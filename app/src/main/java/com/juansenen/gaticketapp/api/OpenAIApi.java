package com.juansenen.gaticketapp.api;

import static com.juansenen.gaticketapp.api.Constantes.BASE_OPENAAI;
import static com.juansenen.gaticketapp.api.Constantes.BASE_URL;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OpenAIApi {

    public static OpenAIInterface buildInstance(String apiKey) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openai.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(OpenAIInterface.class);
    }

}
