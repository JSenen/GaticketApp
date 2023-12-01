package com.juansenen.gaticketapp.model;

import android.util.Log;

import com.juansenen.gaticketapp.api.GaticketApi;
import com.juansenen.gaticketapp.api.GaticketApiInterface;
import com.juansenen.gaticketapp.contract.AdminCloseIncidenceContract;
import com.juansenen.gaticketapp.contract.AdminDetailContract;
import com.juansenen.gaticketapp.domain.Incidences;
import com.juansenen.gaticketapp.domain.IncidencesHistory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminCloseIncidenceModel implements AdminCloseIncidenceContract.model {
    @Override
    public void makeRequestIncidence(modelHisotryListener listener, String incidenceId) {
        Log.d("TAG","Model history call api to search incidence id= "+incidenceId);
        GaticketApiInterface api = GaticketApi.buildInstancce();
        Call<Incidences> incidenceCall = api.loadIncidenceById(incidenceId);

        incidenceCall.enqueue(new Callback<Incidences>() {
            @Override
            public void onResponse(Call<Incidences> call, Response<Incidences> response) {
                if (response.isSuccessful()){
                    Incidences incidenceSearch = response.body();
                    Log.d("TAG","Model history response call api to search incidence id= "+incidenceSearch.getIncidenceCommit().toString());
                    listener.getDataIncidenceSearch(incidenceSearch);
                }
            }

            @Override
            public void onFailure(Call<Incidences> call, Throwable t) {

            }
        });
    }

    @Override
    public void saveIncidenceHisotry(modelHisotryListener listener, IncidencesHistory incidencesHistory) {
        GaticketApiInterface api = GaticketApi.buildInstancce();
        Call<IncidencesHistory> history = api.saveEndIncidence(incidencesHistory);

        history.enqueue(new Callback<IncidencesHistory>() {
            @Override
            public void onResponse(Call<IncidencesHistory> call, Response<IncidencesHistory> response) {
                if (response.isSuccessful()){
                    listener.saveToApiHistory();
                }
            }

            @Override
            public void onFailure(Call<IncidencesHistory> call, Throwable t) {

            }
        });
    }

    @Override
    public void deleteIncidenceSaved(modelHisotryListener listener, String incidenceId) {
        GaticketApiInterface api = GaticketApi.buildInstancce();
        Call<Void> incidenceArchive = api.deleteAfterSave(incidenceId);

        incidenceArchive.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()){
                    String message = "Incidencia archivada";
                    listener.deleteAfterSave(message);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }
}
