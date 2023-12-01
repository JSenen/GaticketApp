package com.juansenen.gaticketapp.model;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.juansenen.gaticketapp.api.OpenAIApi;
import com.juansenen.gaticketapp.api.OpenAIInterface;
import com.juansenen.gaticketapp.contract.AdminChatContract;
import com.juansenen.gaticketapp.domain.ChatRequest;
import com.juansenen.gaticketapp.domain.RespuestaModel;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminChatModel implements AdminChatContract.model {

    private final String apiKey;

    public AdminChatModel(Context context) {
        // Lee la clave de API desde el archivo config.properties en assets
        this.apiKey = readApiKeyFromAssets(context);

    }
    private String readApiKeyFromAssets(Context context) {
        try {
            // Obtiene el AssetManager para acceder a los recursos en assets
            AssetManager assetManager = context.getAssets();

            // Abre el archivo config.properties desde assets
            InputStream inputStream = assetManager.open("config.properties");
            Properties properties = new Properties();
            properties.load(inputStream);

            // Lee la propiedad "api.key" desde el archivo
            return properties.getProperty("api.key");

        } catch (IOException e) {
            Log.e("TAG", "Error al leer el archivo config.properties", e);
            return null;
        }
    }

    @Override
    public void talkToChatGPT(OnChatListener listener, ChatRequest chatRequest) {
        OpenAIInterface apiOpenAI = OpenAIApi.buildInstance(apiKey);
        Call<RespuestaModel> respuestaModelCall = apiOpenAI.talkToChatGpt("Bearer " + apiKey, chatRequest);

        respuestaModelCall.enqueue(new Callback<RespuestaModel>() {

            @Override
            public void onResponse(Call<RespuestaModel> call, Response<RespuestaModel> response) {
                if (response.isSuccessful()) {
                    RespuestaModel respuesta = response.body();
                    if (respuesta != null && respuesta.getChoices() != null && !respuesta.getChoices().isEmpty()) {
                        RespuestaModel.Choice primeraOpcion = respuesta.getChoices().get(0);
                        if (primeraOpcion != null && primeraOpcion.getMessage() != null) {
                            RespuestaModel.Message mensaje = primeraOpcion.getMessage();
                            if (mensaje != null) {
                                String contenido = mensaje.getContent();
                                Log.d("TAG", "Respuesta Content: " + contenido);
                                listener.onChatFinish(contenido);
                            } else {
                                Log.e("TAG", "El objeto 'message' es nulo");
                            }
                        } else {
                            Log.e("TAG", "La primera opción o el mensaje es nulo");
                        }
                    } else {
                        Log.e("TAG", "La lista 'choices' es nula o está vacía");
                    }
                } else {
                    try {
                        String errorBody = response.errorBody().string();
                        Log.e("TAG", "Error en la respuesta: " + response.code() + " - " + errorBody);
                    } catch (IOException e) {
                        Log.e("TAG", "Error al leer el cuerpo del error", e);
                    }
                }
            }
            @Override
            public void onFailure(Call<RespuestaModel> call, Throwable t) {
                Log.e("TAG", "Error en la solicitud a la API", t);
            }
        });
    }
}