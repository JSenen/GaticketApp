package com.juansenen.gaticketapp.api;

import com.juansenen.gaticketapp.domain.ChatRequest;
import com.juansenen.gaticketapp.domain.RespuestaModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface OpenAIInterface {

    @POST("v1/chat/completions")
    Call<RespuestaModel> talkToChatGpt(@Header("Authorization") String apiKey, @Body ChatRequest chatRequest);

}
