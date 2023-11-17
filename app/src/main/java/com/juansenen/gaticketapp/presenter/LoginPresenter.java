package com.juansenen.gaticketapp.presenter;

import android.content.Context;

import com.juansenen.gaticketapp.contract.LoginContract;
import com.juansenen.gaticketapp.domain.User;
import com.juansenen.gaticketapp.model.LoginModel;
import com.juansenen.gaticketapp.util.PasswordUtil;
import com.juansenen.gaticketapp.view.MainActivity;

import java.util.List;

public class LoginPresenter implements LoginContract.Presenter, LoginContract.Model.OnLoadLinesListener {

    private LoginModel model;
    private MainActivity view;
    private Context context; //Necesario para snackbar


    public LoginPresenter(MainActivity view,Context context){
        this.view = view;
        this.context = context;
        this.model = new LoginModel();

    }
    @Override
    public void login(String userTip, String userPass) {
        //Llamada a recoger los datos
        model.loadUser(this, userTip, userPass);

    }

    @Override
    public void onLoadUserSuccess(User user) {
        view.showSnckBar("Login correcto");
        //TODO: Derivar a view dependiendo de Rol

    }

    @Override
    public void onLoadUserError(String message) {

    }
}
