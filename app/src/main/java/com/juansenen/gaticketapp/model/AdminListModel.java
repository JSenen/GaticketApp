package com.juansenen.gaticketapp.model;

import com.juansenen.gaticketapp.api.GaticketApi;
import com.juansenen.gaticketapp.api.GaticketApiInterface;
import com.juansenen.gaticketapp.contract.AdminListContract;
import com.juansenen.gaticketapp.domain.Incidences;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminListModel implements AdminListContract.model {
    @Override
    public void loadIncidencesAdmin(loadIncidencesListener listener, String userId) {
        GaticketApiInterface apiInterface = GaticketApi.buildInstancce();
        Call<List<Incidences>> incidencesList = apiInterface.loadAllIncidences();

        incidencesList.enqueue(new Callback<List<Incidences>>() {
            @Override
            public void onResponse(Call<List<Incidences>> call, Response<List<Incidences>> response) {
                if (response.isSuccessful()){
                    List<Incidences> incidences = response.body();
                    listener.onLoadIncidencesSuccess(incidences);
                }
            }

            @Override
            public void onFailure(Call<List<Incidences>> call, Throwable t) {
                t.printStackTrace();
                String message = "Error llamada a la API";
                listener.onLoadIncidencesError(message);
            }
        });
    }
}
