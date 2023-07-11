package dev.android.appbusesdriver.database;

import java.util.List;

import dev.android.appbusesdriver.models.Bus;
import dev.android.appbusesdriver.models.Contrasenia;
import dev.android.appbusesdriver.models.Detalle_Compra;
import dev.android.appbusesdriver.models.Horarios;
import dev.android.appbusesdriver.models.Usuario;
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
    Call<List<Detalle_Compra>> getPassengers(@Query("hora_salida_viaje") String hora_salida_viaje,
                                             @Query("fecha_viaje") String fecha_viaje);

    @GET("obtenerNumBus.php")
    Call<Bus> getBusNumber(@Query("id_socio") Integer id_socio);
}

