package com.juansenen.gaticketapp.api;

import com.juansenen.gaticketapp.domain.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Interface con las peticiones a la Api
 */
public interface GaticketApiInterface {

    @GET("users")
    Call<List<User>> getUser(@Query("userTip") String userTip);
}
