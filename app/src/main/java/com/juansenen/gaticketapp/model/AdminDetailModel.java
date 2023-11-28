package com.juansenen.gaticketapp.model;

import android.util.Log;

import com.google.gson.Gson;
import com.juansenen.gaticketapp.api.GaticketApi;
import com.juansenen.gaticketapp.api.GaticketApiInterface;
import com.juansenen.gaticketapp.contract.AdminDetailContract;
import com.juansenen.gaticketapp.domain.Department;
import com.juansenen.gaticketapp.domain.Incidences;
import com.juansenen.gaticketapp.domain.Messages;

import java.util.ArrayList;
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

    @Override
    public void getDepartmentofUSer(changeIncidencesListener listener, long userId) {
        String idString = String.valueOf(userId);
        GaticketApiInterface api = GaticketApi.buildInstancce();
        Call<Department> departmentCall = api.getDepartmentUser(idString);

        departmentCall.enqueue(new Callback<Department>() {
            @Override
            public void onResponse(Call<Department> call, Response<Department> response) {
                if (response.isSuccessful()){
                    Department department = response.body();
                    listener.getDepartmentDat(department);
                }
            }

            @Override
            public void onFailure(Call<Department> call, Throwable t) {

            }
        });

    }

    @Override
    public void getAllMessages(changeIncidencesListener listener, long idIncidence) {
        String id = String.valueOf(idIncidence);
        GaticketApiInterface api = GaticketApi.buildInstancce();
        Call<List<Messages>> messagelist = api.getMessages(id);
        Log.d("TAG","Model call to api IdIncidence = "+id);
        messagelist.enqueue(new Callback<List<Messages>>() {
            @Override
            public void onResponse(Call<List<Messages>> call, Response<List<Messages>> response) {
                if (response.isSuccessful()){
                    List<Messages> messages = response.body();
                    Log.d("TAG","Response call to lister contract = "+ messages);
                    listener.getMessagesOK(messages);
                }
            }

            @Override
            public void onFailure(Call<List<Messages>> call, Throwable t) {

            }
        });
    }

}
