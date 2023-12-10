package com.juansenen.gaticketapp.api;

import static com.juansenen.gaticketapp.api.Constantes.BASE_URL;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Clase usada para crear las instancias a la Api por medio de retrofit
 */
public class GaticketApi {

    public static GaticketApiInterface buildInstancce(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL) //URL Base API
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(GaticketApiInterface.class);

    }
}
