package com.project.sw.wohoma.api.webservice;

import com.project.sw.wohoma.api.model.AsistenciaModel;
import com.project.sw.wohoma.api.model.AsistenciaRequest;
import com.project.sw.wohoma.api.model.LoginRequest;
import com.project.sw.wohoma.api.model.LoginResponse;
import com.project.sw.wohoma.api.model.PermisoModel;
import com.project.sw.wohoma.api.model.PermisoRequest;
import com.project.sw.wohoma.api.model.TiendaModel;
import com.project.sw.wohoma.api.model.TiendaRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {

    @POST("/login/")
    @Headers({"Accept: application/json"})
    Call<LoginResponse> validateLogin(@Body LoginRequest request);

    @GET("permiso/empleado/{id}")
    Call<List<PermisoModel>> getListaPermisoById(@Path("id") int idEmpleado);

    @POST("/permiso/nuevo/")
    @Headers({"Accept: application/json"})
    Call<PermisoModel> enviarPermiso(@Body PermisoRequest request);

    @GET("/tienda/lista/")
    Call<List<TiendaModel>> getTiendas();

    @POST("/tienda/nuevo/")
    @Headers({"Accept: application/json"})
    Call<TiendaModel> saveTienda(@Body TiendaRequest request);

    @POST("/asistencia/nuevo/")
    @Headers({"Accept: application/json"})
    Call<AsistenciaModel> marcarEntrada(@Body AsistenciaRequest request);

    @POST("/asistencia/editar/{id}")
    @Headers({"Accept: application/json"})
    Call<AsistenciaModel> marcarSalida(@Body AsistenciaRequest request,
                                       @Path("id") int id_empleado);

    @GET("/asistencia/empleado/{id}")
    @Headers({"Accept: application/json"})
    Call<List<AsistenciaModel>> getAsistencia(@Path("id") int idEmpleado);

}
