package dev.android.appbusesdriver

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.google.firebase.auth.FirebaseAuth
import dev.android.appbusesdriver.database.api
import dev.android.appbusesdriver.databinding.ActivityLoginBinding
import dev.android.appbusesdriver.models.Contrasenia
import dev.android.appbusesdriver.models.Usuario
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLoginBinding
    private var password: String = ""
    private lateinit var user: Usuario

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(binding.root)

        login()

        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun login(){
        binding.btnNext.setOnClickListener {
            if (binding.editTextTextPassword.text.toString().isNotEmpty() && binding.editTextTextPassword1.text.toString().isNotEmpty()){
                getUser(binding.editTextTextPassword.text.toString())
            } else {
                Toast.makeText(this@LoginActivity, "Llene todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun sessionSuccessful (pswd: String, usuario: Usuario) {
        if(usuario.tipo_usuario == "socio") {
            if (binding.editTextTextPassword1.text.toString() == pswd) {
                val intent =
                    Intent(this, MainActivity::class.java).apply {
                        putExtra("email", usuario.email_usuario)
                    }
                startActivity(intent)
            } else {
                showAlert()
            }
        } else {
            showAlert()
        }
    }

    private fun showAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Ingrese correctamente su usuario y contraseña")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
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
                            if (user.email_usuario != null) {
                                getPassword(user)
                            } else {
                                showAlert()                            }
                        } else {
                            Toast.makeText(this@LoginActivity, "Correo incorrecto", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        // Manejar el caso de respuesta no exitosa
                        Toast.makeText(this@LoginActivity, "Correo incorrecto", Toast.LENGTH_SHORT).show()
                    }

                }
            }
        )
    }

    fun getPassword(usuario: Usuario){
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://nilotic-quart.000webhostapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api::class.java)
        val retrofit = retrofitBuilder.getPassword(usuario.email_usuario)
        retrofit.enqueue(
            object : Callback<Contrasenia> {
                override fun onFailure(call: Call<Contrasenia>, t: Throwable) {
                    Log.d("Agregar", t.message.toString())
                }
                @RequiresApi(Build.VERSION_CODES.O)
                override fun onResponse(call: Call<Contrasenia>, response: Response<Contrasenia>) {
                    if (response.isSuccessful) {
                        val contrasenia = response.body()
                        Log.d("Respuesta", contrasenia.toString())
                        if (contrasenia != null) {
                            password = contrasenia.clave_usuario
                            if (password != null) {
                                sessionSuccessful(password, usuario)
                            }
                        }
                    } else {
                        // Manejar el caso de respuesta no exitosa
                        Toast.makeText(this@LoginActivity, "Contraseña incorrecta", Toast.LENGTH_SHORT).show()
                    }

                }
            }
        )
    }
}