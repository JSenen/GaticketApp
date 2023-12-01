package com.juansenen.gaticketapp.presenter;

import android.content.Context;

import com.juansenen.gaticketapp.contract.UserDetailContract;
import com.juansenen.gaticketapp.domain.Messages;
import com.juansenen.gaticketapp.model.UserDetailModel;
import com.juansenen.gaticketapp.view.UserDetailActivity;

import java.util.List;

public class UserDetailPresenter implements UserDetailContract.presenter, UserDetailContract.model.listenerMessages {

    private UserDetailActivity view;
    private UserDetailModel model;
    private Context context;

    public UserDetailPresenter(UserDetailActivity view, Context context){
        this.view = view;
        this.context = context;
        this.model = new UserDetailModel();
    }

    public void getUserMessages(long incidenceId) {
        model.getMessagesIncidence(this,incidenceId);
    }
    @Override
    public void onSuccess(List<Messages> messages) {
        view.showAllMessages(messages);
    }

    @Override
    public void onError() {

    }

    public void sendMessage(long userId, long incidenceId, Messages messageBody) {
        model.sendUserMessage(this,userId,incidenceId,messageBody);
    }
    @Override
    public void sendOK(Messages messages) {
        view.sendMenssageOk();
    }

    @Override
    public void sendFault() {

    }

}
