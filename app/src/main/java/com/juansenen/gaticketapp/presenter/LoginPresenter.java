package com.juansenen.gaticketapp.presenter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.juansenen.gaticketapp.contract.LoginContract;
import com.juansenen.gaticketapp.domain.User;
import com.juansenen.gaticketapp.model.LoginModel;
import com.juansenen.gaticketapp.util.PasswordUtil;
import com.juansenen.gaticketapp.view.MainActivity;
import com.juansenen.gaticketapp.view.UserMainActivity;

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
        if (user != null ){
            String rol = user.getUserRol();
            Log.d("TAG","Login como "+rol);
            if (rol.equals(rol)){
                view.showSnckBar("Login USUARIO correcto");
                // Iniciar la actividad de usuario
                Intent userIntent = new Intent(context, UserMainActivity.class);
                context.startActivity(userIntent);
            }  else if (rol.equals(rol)){
                view.showSnckBar("Login ADMINISTRADOR correcto");
                //Iniciar la actividad de administrador
                // TODO Realizar pantalla administrador

            }
        }
        view.showSnckBar("Error en la conexión");


    }

    @Override
    public void onLoadUserError(String message) {

    }
}
