package com.juansenen.gaticketapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.juansenen.gaticketapp.R;
import com.juansenen.gaticketapp.contract.UserContract;
import com.juansenen.gaticketapp.presenter.UserPresenter;

public class UserMainActivity extends AppCompatActivity implements UserContract.view {

    private UserPresenter presenter;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);
    }

    /**
     * Menus disponibles en el ActionBar
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.user_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.item_usermenu_saveticket) {
            //Enviar a la pantalla grabar ticket
/*
            Intent intent = new Intent(this, LogedModLinesActivityView.class);
            finish();
            startActivity(intent);

            return true;
*/

        } else if (item.getItemId() == R.id.item_usermenu_exit) {
            //Enviar a la pagina de login inicial
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent); // Cambiamos de Activity
            finish(); // Cerramos la actual

        }

        return false;
    }
}