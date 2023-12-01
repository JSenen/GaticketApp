package com.juansenen.gaticketapp.model;

import com.juansenen.gaticketapp.api.GaticketApi;
import com.juansenen.gaticketapp.api.GaticketApiInterface;
import com.juansenen.gaticketapp.contract.UserDetailContract;
import com.juansenen.gaticketapp.domain.Messages;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserDetailModel implements UserDetailContract.model {
    @Override
    public void getMessagesIncidence(listenerMessages listener, long idIncidence) {
        String id = String.valueOf(idIncidence);
        GaticketApiInterface api = GaticketApi.buildInstancce();
        Call<List<Messages>> messages = api.getMessages(id);

        messages.enqueue(new Callback<List<Messages>>() {
            @Override
            public void onResponse(Call<List<Messages>> call, Response<List<Messages>> response) {
                if (response.isSuccessful()){
                    List<Messages> messagesList = response.body();
                    listener.onSuccess(messagesList);
                }
            }

            @Override
            public void onFailure(Call<List<Messages>> call, Throwable t) {

            }
        });

    }

    @Override
    public void sendUserMessage(listenerMessages listener,long userId, long incidenceId, Messages messageBody) {
        String idUser = String.valueOf(userId);
        String idIncidence = String.valueOf(incidenceId);
        GaticketApiInterface api = GaticketApi.buildInstancce();
        Call<Messages> messages = api.sendMessage(idIncidence,idUser,messageBody);

        messages.enqueue(new Callback<Messages>() {
            @Override
            public void onResponse(Call<Messages> call, Response<Messages> response) {
                if (response.isSuccessful()){
                    Messages messagesRequest = response.body();
                    listener.sendOK(messagesRequest);
                }
            }

            @Override
            public void onFailure(Call<Messages> call, Throwable t) {

            }
        });
    }
}
