package com.juansenen.gaticketapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;
import com.juansenen.gaticketapp.R;
import com.juansenen.gaticketapp.contract.LoginContract;
import com.juansenen.gaticketapp.presenter.LoginPresenter;

public class MainActivity extends AppCompatActivity implements LoginContract.View {

    private LoginPresenter loginPresenter;
    private Context context = this;
    private Button butLogin;
    private EditText edtxtuser;
    private EditText edtxtpasswrd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inicializar vistas
        edtxtuser = findViewById(R.id.edtxt_user);
        edtxtpasswrd = findViewById(R.id.edtxt_passwrd);
        butLogin = findViewById(R.id.butLogin);

        //Inicializar el presenter
        loginPresenter = new LoginPresenter(this,context);

        //Titulo en ActionBar
        getSupportActionBar().setTitle(R.string.Login);

        // Configurar listener para botón de login
        butLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userTip = edtxtuser.getText().toString().trim();
                String userPass = edtxtpasswrd.getText().toString().trim();
                loginPresenter.login(userTip,userPass);
            }
        });
    }

    @Override
    public void showSnckBar(String message) {
        if (message != null) {
            Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT);
            snackbar.show();
        }
    }
}