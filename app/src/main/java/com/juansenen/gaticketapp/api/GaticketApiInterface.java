package com.juansenen.gaticketapp.api;

import com.juansenen.gaticketapp.domain.Department;
import com.juansenen.gaticketapp.domain.Device;
import com.juansenen.gaticketapp.domain.Incidences;
import com.juansenen.gaticketapp.domain.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Interface con las peticiones a la Api
 */
public interface GaticketApiInterface {

    @GET("users")
    Call<List<User>> getUser(@Query("userTip") String userTip);

    @GET("device")
    Call<List<Device>> getDeviceBySerial(@Query("deviceSerial") String deviceSerial);
    @GET("device")
    Call<List<Device>> getDeviceByMac(@Query("mac") String mac);
    @GET("incidences/user/{userId}")
    Call<List<Incidences>> loadAllIncidencesUser(@Path("userId") String userId);
    @GET("incidences")
    Call<List<Incidences>> loadAllIncidences();
    @GET("incidences/{incidenceId}")
    Call<Incidences> loadIncidenceById(@Path("incidenceId") String incidenceId);
    @PATCH("incidence/{incidenceId}")
    Call<Incidences> changeStatus(@Path("incidenceId") String incidenceId, @Body Incidences incidenceBody );
    @PATCH("incidence/admin/{idIncidence}")
    Call<Incidences> changeAdminIncidence(@Path("idIncidence") String incidenceId, @Body Incidences incidenceBody);

    @POST("incidence/{userId}")
    Call<Incidences> addIncidence(@Path("userId") String id, @Body Incidences incidenceBody);

    @GET("department/{idUser}")
    Call<Department> getDepartmentUser(@Path("idUser") String idUser);

}
