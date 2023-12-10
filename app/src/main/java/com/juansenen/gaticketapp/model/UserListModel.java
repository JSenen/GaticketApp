package com.juansenen.gaticketapp.model;

import android.util.Log;

import com.juansenen.gaticketapp.api.GaticketApi;
import com.juansenen.gaticketapp.api.GaticketApiInterface;
import com.juansenen.gaticketapp.contract.UserContract;
import com.juansenen.gaticketapp.contract.UserListContract;
import com.juansenen.gaticketapp.domain.Incidences;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserListModel implements UserListContract.model{


    @Override
    public void loadIncidences(loadIncidencesListener listener, String userId) {

        GaticketApiInterface apiInterface = GaticketApi.buildInstancce();
        Call<List<Incidences>> incidencesList = apiInterface.loadAllIncidencesUser(userId);

        incidencesList.enqueue(new Callback<List<Incidences>>() {
            @Override
            public void onResponse(Call<List<Incidences>> call, Response<List<Incidences>> response) {
                //Recoge resultados
                if (response.body() != null ){
                    List<Incidences> incidences = response.body();
                    listener.loadIncidencesSuccess(incidences);
                    Log.d("TAG", "Código de respuesta: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Incidences>> call, Throwable t) {
                Log.d("TAG", "Código de respuesta: ERROR");
                t.printStackTrace();
                String message = "Error llamada a la API";
                listener.loadIncidencesError(message);

            }
        });


    }
}
