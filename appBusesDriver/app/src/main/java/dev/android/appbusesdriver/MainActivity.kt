package dev.android.appbusesdriver

import android.R
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.zxing.integration.android.IntentIntegrator
import com.squareup.picasso.Picasso
import dev.android.appbusesdriver.database.api
import dev.android.appbusesdriver.databinding.ActivityMainBinding
import dev.android.appbusesdriver.models.Bus
import dev.android.appbusesdriver.models.Detalle_Compra
import dev.android.appbusesdriver.models.Horarios
import dev.android.appbusesdriver.models.Usuario
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
    private val adapter: PassengersAdapter by lazy{
        PassengersAdapter()
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(binding.root)

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
                        if (pasajeros != null) {
                            adapter.pasajero = pasajeros
                            adapter.notifyDataSetChanged()
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
                            binding.txtBusNumber.text = numero.numero_bus.toString()
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
        integrator.setOrientationLocked(false) // Agrega esta línea
        integrator.initiateScan()
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if(result.contents == null){
                Toast.makeText(this, "Cancelado", Toast.LENGTH_SHORT).show()
            }else{
//                Toast.makeText(this, "El valor escaneado es: ${result.contents}", Toast.LENGTH_SHORT).show()
                id_venta = result.contents.toInt()
                cargarDatosPasajeros(id_venta)
                Toast.makeText(this, "$id_venta número", Toast.LENGTH_SHORT).show()
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}