package dev.android.appbusesdriver.database;

import java.util.List;

import dev.android.appbusesdriver.models.Contrasenia;
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

    @FormUrlEncoded
    @POST("listarHorarioSocio.php")
    Call<List<Horarios>> getTimeDriver(@Field("id_socio") Integer id_socio);
}

