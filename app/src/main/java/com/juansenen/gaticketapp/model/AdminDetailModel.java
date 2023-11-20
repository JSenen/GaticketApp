package com.juansenen.gaticketapp.model;

import com.juansenen.gaticketapp.api.GaticketApi;
import com.juansenen.gaticketapp.api.GaticketApiInterface;
import com.juansenen.gaticketapp.contract.AdminDetailContract;
import com.juansenen.gaticketapp.domain.Incidences;
import com.juansenen.gaticketapp.presenter.AdminDetailPresenter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminDetailModel implements AdminDetailContract.model {

    @Override
    public void changeStatus(changeIncidencesListener listener, long idIncidence, Incidences incidencesBody) {
        String idString = String.valueOf(idIncidence);
        GaticketApiInterface api = GaticketApi.buildInstancce();
        Call<Incidences> incidence = api.changeStatus(idString, incidencesBody);

        incidence.enqueue(new Callback<Incidences>() {
            @Override
            public void onResponse(Call<Incidences> call, Response<Incidences> response) {
                if (response.isSuccessful()){
                    listener.changeStatusOK();
                }
            }

            @Override
            public void onFailure(Call<Incidences> call, Throwable t) {

            }
        });
    }

    @Override
    public void changeAdmin(changeIncidencesListener listener, long idIncidence, Incidences incidenceBody) {
        String idString = String.valueOf(idIncidence);
        GaticketApiInterface api = GaticketApi.buildInstancce();
        Call<Incidences> incidence = api.changeAdminIncidence(idString,incidenceBody);

        incidence.enqueue(new Callback<Incidences>() {
            @Override
            public void onResponse(Call<Incidences> call, Response<Incidences> response) {
                if (response.isSuccessful()){
                    listener.changeAdminOK();
                }
            }

            @Override
            public void onFailure(Call<Incidences> call, Throwable t) {

            }
        });
    }

    @Override
    public void getDataIncidence(changeIncidencesListener listener, long incidenceId) {
        String idString = String.valueOf(incidenceId);
        GaticketApiInterface api = GaticketApi.buildInstancce();
        Call<Incidences> incidence = api.loadIncidenceById(idString);

        incidence.enqueue(new Callback<Incidences>() {
            @Override
            public void onResponse(Call<Incidences> call, Response<Incidences> response) {
                if (response.isSuccessful()){
                    Incidences incidence = response.body();
                    listener.getDataIncidence(incidence);
                }
            }

            @Override
            public void onFailure(Call<Incidences> call, Throwable t) {

            }
        });
    }
}
