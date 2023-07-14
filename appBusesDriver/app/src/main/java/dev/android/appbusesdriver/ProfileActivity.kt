package dev.android.appbusesdriver

import android.content.*
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import dev.android.appbusesdriver.database.api
import dev.android.appbusesdriver.databinding.ActivityProfileBinding
import dev.android.appbusesdriver.models.Usuario
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    private lateinit var user: Usuario
    private lateinit var bundle: Bundle
    private var email = ""

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(binding.root)

        bundle = intent.extras!!

        email = bundle?.getString("email").toString()
//        Toast.makeText(this@ProfileActivity, email.toString(), Toast.LENGTH_SHORT).show()
        if (email != null) {
            getUser(email)
        }

        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnLogout.setOnClickListener {
            cerrarSesion()
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        }

        binding.btnEditInfo.setOnClickListener {
            val intent = Intent(this, ProfileInfoActivity::class.java).apply {
                putExtras(bundle)
            }
            startActivity(intent)
        }

        binding.btnInfo.setOnClickListener {
            val intent = Intent(this, ProfileInfoActivity::class.java).apply {
                putExtras(bundle)
            }
            startActivity(intent)
            finish()
        }

        binding.btnChangePassword.setOnClickListener {
            val intent = Intent(this, ChangePasswordActivity::class.java).apply {
                putExtras(bundle)
            }
            startActivity(intent)
        }
    }

    fun cerrarSesion() {
        val intent = Intent(this, WelcomeActivity::class.java)
        startActivity(intent)
        finishAffinity()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getUser(email_usuario: String) {
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

                override fun onResponse(
                    call: Call<Usuario>,
                    response: retrofit2.Response<Usuario>
                ) {
                    if (response.isSuccessful) {
                        val usuario = response.body()
                        Log.d("Respuesta", usuario.toString())
                        if (usuario != null) {
                            user = usuario
                            val name = user.nombre_usuario.split(" ")
                            val lastName = user.apellido_usuario.split(" ")
                            binding.txtName.text = name[0] + " " + lastName[0]
                            binding.txtFullName.text = user.nombre_usuario + " " + usuario.apellido_usuario
                            Picasso.get().load(usuario.foto_usuario)
                                .error(R.drawable.purple_aesthetic_woman_portrait_7h0yo10dvp884ons)
                                .into(binding.imgProfile)
                        }
                    } else {
                        // Manejar el caso de respuesta no exitosa
                        Toast.makeText(
                            this@ProfileActivity,
                            "No existen elementos",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                }
            }
        )
    }
}