package dev.android.appbusesdriver.models
import java.io.Serializable

data class Usuario (
    val id_usuario: Int,
    val cedula_usuario: String,
    val tipo_usuario: String,
    val email_usuario: String,
    val nombre_usuario: String,
    val apellido_usuario: String,
    val telefono_usuario: String,
    var foto_usuario: String
): Serializable

data class Contrasenia (
    val clave_usuario: String
): Serializable