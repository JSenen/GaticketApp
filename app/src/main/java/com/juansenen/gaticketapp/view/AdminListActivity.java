package com.juansenen.gaticketapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.juansenen.gaticketapp.R;
import com.juansenen.gaticketapp.contract.AdminListContract;
import com.juansenen.gaticketapp.domain.Incidences;
import com.juansenen.gaticketapp.presenter.AdminListPresenter;

import java.util.List;

public class AdminListActivity extends AppCompatActivity implements AdminListContract.view {

    private AdminListPresenter presenter;
    private List<Incidences> incidences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_list);
    }

    /**
     * Opciones menu Action bar
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.admin_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.admin_menu_logout) {
            //Enviar a la pantalla de Inicio
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent); // Cambiamos de Activity
            finish(); // Cerramos la actual
        } else if (item.getItemId() == R.id.admin_menu_list) {
            //Enviar a la pagina listado incidencias
            Intent intent = new Intent(this, AdminListActivity.class);
            startActivity(intent); // Cambiamos de Activity
            finish(); // Cerramos la actual

        }

        return false;
    }
}