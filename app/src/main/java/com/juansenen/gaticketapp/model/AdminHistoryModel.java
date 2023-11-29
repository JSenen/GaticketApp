package com.juansenen.gaticketapp.model;

import com.juansenen.gaticketapp.api.GaticketApi;
import com.juansenen.gaticketapp.api.GaticketApiInterface;
import com.juansenen.gaticketapp.contract.AdminHistoryContract;
import com.juansenen.gaticketapp.domain.IncidencesHistory;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminHistoryModel implements AdminHistoryContract.model {
    @Override
    public void loadHistory(historyListener listener) {

        GaticketApiInterface api = GaticketApi.buildInstancce();
        Call<List<IncidencesHistory>> history = api.getAllHistory();

        history.enqueue(new Callback<List<IncidencesHistory>>() {
            @Override
            public void onResponse(Call<List<IncidencesHistory>> call, Response<List<IncidencesHistory>> response) {
                if (response.isSuccessful()){
                    List<IncidencesHistory> incidencesHistoryList = response.body();
                    listener.onLoadHistory(incidencesHistoryList);
                }
            }

            @Override
            public void onFailure(Call<List<IncidencesHistory>> call, Throwable t) {

            }
        });

    }
}
