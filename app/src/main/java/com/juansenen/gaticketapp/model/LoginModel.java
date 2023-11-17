package com.juansenen.gaticketapp.model;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.juansenen.gaticketapp.api.GaticketApi;
import com.juansenen.gaticketapp.api.GaticketApiInterface;
import com.juansenen.gaticketapp.contract.LoginContract;
import com.juansenen.gaticketapp.domain.User;
import com.juansenen.gaticketapp.util.PasswordUtil;

import java.util.List;

import at.favre.lib.crypto.bcrypt.BCrypt;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginModel implements LoginContract.Model {

    private Context context;

    @Override
    public void loadUser(OnLoadLinesListener listener, String userTip, String userPass) {
        Log.d("TAG", "loadUser: Haciendo llamada a la API user " + userTip);
        //Pasamos userTip a mayusculas y quitamos porsibles espacios en blanco
        String tip = userTip.toUpperCase().trim();
        GaticketApiInterface gaticketApiInterface = GaticketApi.buildInstancce();
        Call<List<User>> callUsers = gaticketApiInterface.getUser(tip);

        callUsers.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                try {
                    Log.d("TAG", "Response: " + new Gson().toJson(response.body()));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (response.body() != null && !response.body().isEmpty()) {
                    List<User> userList = response.body();

                    // Obtenemos el primer usuario de la lista
                    User userSearch = userList.get(0);

                    boolean isPassWordCorrect = PasswordUtil.checkPassword(userPass, userSearch.getUserPassword());

                    if (isPassWordCorrect) {
                        listener.onLoadUserSuccess(userSearch);
                    } else {
                        listener.onLoadUserError("Error: Contraseña incorrecta");
                    }
                    Log.d("TAG", "loadUser: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.d("API GATICKET ", "Llamada erronea" + t);
                t.printStackTrace();
                String message = "Error llamada a la API";
                listener.onLoadUserError(message);
            }
        });
    }
}
