package dev.android.appbusesdriver

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import dev.android.appbusesdriver.database.api
import dev.android.appbusesdriver.databinding.ActivityChangePasswordBinding
import dev.android.appbusesdriver.models.Contrasenia
import dev.android.appbusesdriver.models.Usuario
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ChangePasswordActivity : AppCompatActivity() {
    private lateinit var binding : ActivityChangePasswordBinding
    private lateinit var bundle: Bundle
    private lateinit var user: Usuario
    private lateinit var auth: FirebaseAuth
    private var password: String = ""
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChangePasswordBinding.inflate(layoutInflater)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(binding.root)

        bundle = intent.extras!!
        val email = bundle.getString("email")

        if (email != null) {
            getUser(email)
            getPassword(email)
        }

        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnSave.setOnClickListener {
            if (binding.edtPassword.text.toString().length > 7) {
                if (binding.edtPassword.text.toString() == binding.edtPasswordAgain.text.toString()) {
                    if (binding.edtCurrentPassword.text.toString().isNotEmpty()){
                        if (password == binding.edtCurrentPassword.text.toString()) {
                            changePassword(user, binding.edtPassword.text.toString())
                            finish()
                        } else {
                            Toast.makeText(this@ChangePasswordActivity, "Contraseña actual incorrecta", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(this@ChangePasswordActivity, "Contraseña actual incorrecta", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@ChangePasswordActivity, "Contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this@ChangePasswordActivity, "Mínimo 8 caracteres", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun changePassword(user: Usuario, password: String) {
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://nilotic-quart.000webhostapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api::class.java)
        val retrofit = retrofitBuilder.updateUserPassword(user.id_usuario, password)
        retrofit.enqueue(
            object : Callback<Usuario> {
                override fun onFailure(call: Call<Usuario>, t: Throwable) {
                    Log.d("Agregar", t.message.toString())
                }
                @RequiresApi(Build.VERSION_CODES.O)
                override fun onResponse(call: Call<Usuario>, response: Response<Usuario>) {
                    Log.d("Agregar", response.message().toString())
                }
            }
        )
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
                        }
                    } else {
                        // Manejar el caso de respuesta no exitosa
                        Toast.makeText(this@ChangePasswordActivity, "No existen elementos", Toast.LENGTH_SHORT).show()
                    }

                }
            }
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getPassword(email_usuario: String){
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://nilotic-quart.000webhostapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api::class.java)
        val retrofit = retrofitBuilder.getPassword(email_usuario)
        retrofit.enqueue(
            object : Callback<Contrasenia> {
                override fun onFailure(call: Call<Contrasenia>, t: Throwable) {
                    Log.d("Agregar", t.message.toString())
                }
                override fun onResponse(call: Call<Contrasenia>, response: Response<Contrasenia> ) {
                    if (response.isSuccessful) {
                        val contrasenia = response.body()
                        Log.d("Respuesta", contrasenia.toString())
                        if (contrasenia != null) {
                            password = contrasenia.clave_usuario
                        }
                    } else {
                        // Manejar el caso de respuesta no exitosa
                        Toast.makeText(this@ChangePasswordActivity, "No existen elementos", Toast.LENGTH_SHORT).show()
                    }

                }
            }
        )
    }
}