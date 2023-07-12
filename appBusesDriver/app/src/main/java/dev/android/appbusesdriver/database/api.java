package dev.android.appbusesdriver.database;

import java.util.List;

import dev.android.appbusesdriver.models.Bus;
import dev.android.appbusesdriver.models.Contrasenia;
import dev.android.appbusesdriver.models.Destino;
import dev.android.appbusesdriver.models.Detalle_Compra;
import dev.android.appbusesdriver.models.Frecuencia;
import dev.android.appbusesdriver.models.Horarios;
import dev.android.appbusesdriver.models.Usuario;
import dev.android.appbusesdriver.models.Viaje;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface api {
    @GET("obtenerDatosUsuariosEmail.php")
    Call<Usuario> getUser(@Query("email_usuario")String email_usuario);

    @GET("obtenerClavePasajero.php")
    Call<Contrasenia> getPassword(@Query("email_usuario")String email_usuario);

    @GET("listarHorarioSocio.php")
    Call<List<Horarios>> getTimeDriver(@Query("id_socio") Integer id_socio);

    @GET("listarDetalleVenta.php")
    Call<List<Detalle_Compra>> getPassengers(@Query("id_venta_pertenece") Integer id_venta_pertenece);

    @GET("obtenerIdViaje.php")
    Call<Viaje> getIdTrip(@Query("hora_salida_viaje") String hora_salida_viaje,
                          @Query("fecha_viaje") String fecha_viaje,
                          @Query("bus_viaje") Integer bus_viaje);

    @GET("obtenerNumBus.php")
    Call<Bus> getBusNumber(@Query("id_socio") Integer id_socio);

    @GET("mostrarPasajerosViaje.php")
    Call<List<Frecuencia>> getPassengersTrip(@Query("id_viaje") Integer id_viaje);

    @GET("obtenerDestinoHora.php")
    Call<List<Destino>> getDestination(@Query("fecha_viaje") String fecha_viaje,
                                 @Query("hora_salida_viaje") String hora_salida_viaje);

    @FormUrlEncoded
    @POST("editarPerfilPasajero.php")
    Call<Usuario> updateUser(@Field("id_usuario") Integer id_usuario,
                             @Field("email_usuario") String email_usuario,
//                             @Field("tipo_usuario") String tipo_usuario,
                             @Field("nombre_usuario") String nombre_usuario,
                             @Field("apellido_usuario") String apellido_usuario,
                             @Field("telefono_usuario") String telefono_usuario,
                             @Field("foto_usuario") String foto_usuario);

    @FormUrlEncoded
    @POST("editarClaveUsuario.php")
    Call<Usuario> updateUserPassword(@Field("id_usuario") Integer id_usuario,
                                     @Field("clave_usuario") String clave_usuario);
}

