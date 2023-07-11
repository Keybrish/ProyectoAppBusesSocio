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

data class Horarios (
    val hora_salida_viaje: String,
    val fecha_viaje: String
): Serializable

data class Detalle_Compra(
    val id_venta_pertenece: Int,
    val id_asiento: Int,
    val cedula_pasajero: String,
    val foto_usuario: String
): Serializable

data class Bus (
    val id_bus: Int,
    val numero_bus: Int
): Serializable