package dev.android.appbusesdriver

import android.R
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.widget.ArrayAdapter
import android.widget.FrameLayout
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.zxing.integration.android.IntentIntegrator
import com.squareup.picasso.Picasso
import dev.android.appbusesdriver.database.api
import dev.android.appbusesdriver.databinding.ActivityMainBinding
import dev.android.appbusesdriver.models.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private var email = ""
    private lateinit var bundle: Bundle
    private lateinit var user : Usuario
    private var id_venta = 0
    val op = mutableListOf<String>()
    private var passgs: List<Frecuencia>? = null
    private var date = ""
    var id_viaje = 0
    private val adapter: PassengersAdapter by lazy{
        PassengersAdapter()
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnActive.setColorFilter(ContextCompat.getColor(this, R.color.holo_red_dark))
        binding.txtEmpty.visibility = View.GONE
        bundle = intent.extras!!
        email = bundle.getString("email").toString()

        getUser(email)
        op.add("Sin horarios")
        val sp = ArrayAdapter(this@MainActivity, R.layout.simple_spinner_item, op)
        binding.spinner.adapter = sp

        binding.btnProfile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java).apply {
            }
            startActivity(intent)
        }

        binding.btnScanner.setOnClickListener {
            initScanner()
        }
    }

    fun cargarDatos(id_socio: Int){
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://nilotic-quart.000webhostapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api::class.java)
        val retrofit = retrofitBuilder.getTimeDriver(id_socio)
        retrofit.enqueue(
            object : Callback<List<Horarios>> {
                override fun onFailure(call: Call<List<Horarios>>, t: Throwable) {
                    Log.d("Agregar", t.message.toString())
                    Log.d("Socio", id_socio.toString())
                }
                override fun onResponse(call: Call<List<Horarios>>, response: retrofit2.Response<List<Horarios>> ) {
                    if (response.isSuccessful) {
                        op.clear()
                        val horarios = response.body()
                        val time = horarios as MutableList<Horarios>
                        Log.d("Respuesta", time.toString())
                        if (horarios != null) {
                            binding.spinner.dropDownVerticalOffset
                            for (element in horarios){
                                op.add(element.hora_salida_viaje.substring(0,5))
                            }
                            date = horarios[0].fecha_viaje
                            val sp = ArrayAdapter(this@MainActivity, R.layout.simple_spinner_item, op)
                            binding.spinner.adapter = sp
                        }
                    } else {
                        // Manejar el caso de respuesta no exitosa
                    }
                }
            }
        )
    }

    fun cargarDatosPasajeros(id_venta_pertenece: Int){
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://nilotic-quart.000webhostapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api::class.java)
        val retrofit = retrofitBuilder.getPassengers(id_venta_pertenece)
        retrofit.enqueue(
            object : Callback<List<Detalle_Compra>> {
                override fun onFailure(call: Call<List<Detalle_Compra>>, t: Throwable) {
                    Log.d("Agregar", t.message.toString())
                    Log.d("Socio", id_venta_pertenece.toString())
                }
                override fun onResponse(call: Call<List<Detalle_Compra>>, response: retrofit2.Response<List<Detalle_Compra>> ) {
                    if (response.isSuccessful) {
                        val pasajeros = response.body()
                        val passengers = pasajeros as MutableList<Detalle_Compra>
                        Log.d("Respuesta", passengers.toString())
                        if (pasajeros != null && pasajeros.isNotEmpty()) {
                            adapter.pasajero = pasajeros
                            adapter.notifyDataSetChanged()
                        } else {
                            adapter.pasajero = emptyList()
                        }
                    } else {
                        adapter.pasajero = emptyList()
                    }
                }
            }
        )
        binding.rvPassengers.adapter = adapter
        binding.rvPassengers.layoutManager = LinearLayoutManager(this)
        binding.rvPassengers.setHasFixedSize(true)
    }

    fun getBusNumber(id_socio: Int){
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://nilotic-quart.000webhostapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api::class.java)
        val retrofit = retrofitBuilder.getBusNumber(id_socio)
        retrofit.enqueue(
            object : Callback<Bus> {
                override fun onFailure(call: Call<Bus>, t: Throwable) {
                    Log.d("Agregar", t.message.toString())
                    Log.d("Socio", id_socio.toString())
                }
                override fun onResponse(call: Call<Bus>, response: retrofit2.Response<Bus> ) {
                    if (response.isSuccessful) {
                        val numero = response.body()
                        Log.d("Respuesta", numero.toString())
                        if (numero != null) {
                            binding.txtBusNumber.text = "Bus asignado: ${numero.numero_bus}"
                            getIdTrip(binding.spinner.selectedItem.toString(), date, numero.id_bus)
                        }
                    } else {
                        // Manejar el caso de respuesta no exitosa
                        Toast.makeText(this@MainActivity, "No existen elementos", Toast.LENGTH_SHORT).show()
                    }

                }
            }
        )
        binding.rvPassengers.adapter = adapter
        binding.rvPassengers.layoutManager = LinearLayoutManager(this)
        binding.rvPassengers.setHasFixedSize(true)
    }

    fun getIdTrip(time: String, date: String, id_bus: Int){
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://nilotic-quart.000webhostapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api::class.java)
        val retrofit = retrofitBuilder.getIdTrip("$time:00", date, id_bus)
        retrofit.enqueue(
            object : Callback<Viaje> {
                override fun onFailure(call: Call<Viaje>, t: Throwable) {
                    Log.d("Agregar", t.message.toString())
                }
                override fun onResponse(call: Call<Viaje>, response: retrofit2.Response<Viaje> ) {
                    if (response.isSuccessful) {
                        val numero = response.body()
                        Log.d("Respuesta", numero.toString())
                        if (numero != null) {
                            id_viaje = numero.id_viaje
                            getPassengersTrip(id_viaje)
                            binding.btnActive.setColorFilter(ContextCompat.getColor(this@MainActivity, R.color.holo_green_light))
//                            Toast.makeText(this@MainActivity, "Información cargada", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        // Manejar el caso de respuesta no exitosa
                        Toast.makeText(this@MainActivity, "No existen elementos", Toast.LENGTH_SHORT).show()
                    }

                }
            }
        )
        binding.rvPassengers.adapter = adapter
        binding.rvPassengers.layoutManager = LinearLayoutManager(this)
        binding.rvPassengers.setHasFixedSize(true)
    }

    fun getPassengersTrip(id_venta_pertenece: Int){
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://nilotic-quart.000webhostapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api::class.java)
        val retrofit = retrofitBuilder.getPassengersTrip(id_venta_pertenece)
        retrofit.enqueue(
            object : Callback<List<Frecuencia>> {
                override fun onFailure(call: Call<List<Frecuencia>>, t: Throwable) {
                    Log.d("Agregar", t.message.toString())
                    Log.d("Socio", id_venta_pertenece.toString())
                }
                override fun onResponse(call: Call<List<Frecuencia>>, response: retrofit2.Response<List<Frecuencia>> ) {
                    if (response.isSuccessful) {
                        val pasajeros = response.body()
                        Log.d("Respuesta", pasajeros.toString())
                        if (pasajeros != null) {
                            passgs = pasajeros
                        }
                    } else {
                        // Manejar el caso de respuesta no exitosa
                        Toast.makeText(this@MainActivity, "No existen elementos", Toast.LENGTH_SHORT).show()
                    }

                }
            }
        )
        binding.rvPassengers.adapter = adapter
        binding.rvPassengers.layoutManager = LinearLayoutManager(this)
        binding.rvPassengers.setHasFixedSize(true)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getUser(email_usuario: String){
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://nilotic-quart.000webhostapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api::class.java)
        val retrofit = retrofitBuilder.getUser(email_usuario)
        retrofit.enqueue(
            object : Callback<Usuario> {
                override fun onFailure(call: Call<Usuario>, t: Throwable) {
                    Log.d("Agregar", t.message.toString())
                }
                override fun onResponse(call: Call<Usuario>, response: retrofit2.Response<Usuario> ) {
                    if (response.isSuccessful) {
                        val usuario = response.body()
                        Log.d("Respuesta", usuario.toString())
                        if (usuario != null) {
                            user = usuario
                            binding.txtID.text = user.cedula_usuario
                            binding.txtName.text = user.nombre_usuario + " " + user.apellido_usuario
                            Log.d("Usuario", user.toString())
                            getBusNumber(user.id_usuario)
                            cargarDatos(user.id_usuario)
                        }
                    } else {
                        // Manejar el caso de respuesta no exitosa
                        Toast.makeText(this@MainActivity, "No existen elementos", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        )
    }

    private fun initScanner() {
        val integrator = IntentIntegrator(this)
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
        integrator.setPrompt("Escanear pasajero")
        integrator.setOrientationLocked(false)
        integrator.setBeepEnabled(false) // Desactivar el sonido
        integrator.initiateScan()
    }


    @RequiresApi(Build.VERSION_CODES.O)
    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if(result.contents == null){
                Toast.makeText(this, "Cancelado", Toast.LENGTH_SHORT).show()
            }else{
                // Obtener una instancia del Vibrator
                val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
                // Comprobar si el dispositivo tiene capacidad de vibración
                if (vibrator.hasVibrator()) {
                    // Duración de la vibración en milisegundos
                    val vibrationDuration = 100L
                    // Crear un efecto de vibración
                    val vibrationEffect = VibrationEffect.createOneShot(
                        vibrationDuration,
                        VibrationEffect.DEFAULT_AMPLITUDE
                    )
                    // Vibrar con el efecto configurado
                    vibrator.vibrate(vibrationEffect)
                }
                try {
                    id_venta = result.contents.toInt()
                    // Continuar con el código después de la conversión exitosa

                    var vacio = true
                    if (passgs != null) {
                        for (i in passgs!!.indices){
                            if (passgs!![i].id_venta_pertenece == id_venta) {
                                binding.txtEmpty.visibility = View.GONE
                                adapter.pasajero = emptyList()
                                cargarDatosPasajeros(id_venta)
//                                Toast.makeText(this, "Número $id_venta", Toast.LENGTH_SHORT).show()
                                vacio = false
                                break
                            }
                        }
                    } else {
//                    Toast.makeText(this@MainActivity, "Pasajero no registrado", Toast.LENGTH_SHORT).show()
                        showAlert()
                    }

                    if (vacio) {
                        adapter.pasajero = emptyList()
                        adapter.notifyDataSetChanged()
                        binding.txtEmpty.visibility = View.VISIBLE
//                    Toast.makeText(this@MainActivity, "En esta venta no hay pasajeros registrados", Toast.LENGTH_SHORT).show()
                    }
                } catch (e: NumberFormatException) {
                    // Mostrar una alerta en caso de error de conversión
                    showAlert()
                }
            }
        } else {
            Toast.makeText(this@MainActivity, "Pasajero no registrado", Toast.LENGTH_SHORT).show()
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun showAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Código no registrado.")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onResume() {
        super.onResume()
        binding.btnActive.setColorFilter(ContextCompat.getColor(this, R.color.holo_red_dark))
        getUser(email)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }
}