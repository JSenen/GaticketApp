package com.juansenen.gaticketapp.presenter;

import android.content.Context;
import android.util.Log;

import com.juansenen.gaticketapp.contract.AdminChatContract;
import com.juansenen.gaticketapp.domain.ChatRequest;
import com.juansenen.gaticketapp.domain.RespuestaModel;
import com.juansenen.gaticketapp.model.AdminChatModel;
import com.juansenen.gaticketapp.view.AdminChatActivity;

public class AdminChatPresenter implements AdminChatContract.presenter, AdminChatContract.model.OnChatListener {

    private AdminChatActivity view;
    private AdminChatModel model;
    private Context context;

    public AdminChatPresenter(AdminChatActivity view, Context context){
        this.context = context;
        this.view = view;
        this.model = new AdminChatModel(view.getApplicationContext());
    }
    public void questionToChat(ChatRequest chatRequest){
        Log.d("TAG","Presenter call to model question " + chatRequest);
        model.talkToChatGPT(this,chatRequest);
    }
    @Override
    public void onChatFinish(String response) {
        view.showResponse(response);
    }

    @Override
    public void onChatError(String response) {

    }
}
