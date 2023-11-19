package com.juansenen.gaticketapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.snackbar.Snackbar;
import com.juansenen.gaticketapp.R;
import com.juansenen.gaticketapp.adapter.AdminListAdapter;
import com.juansenen.gaticketapp.adapter.UserListAdapter;
import com.juansenen.gaticketapp.contract.AdminListContract;
import com.juansenen.gaticketapp.domain.Incidences;
import com.juansenen.gaticketapp.presenter.AdminListPresenter;

import java.util.ArrayList;
import java.util.List;

public class AdminListActivity extends AppCompatActivity implements AdminListContract.view {

    private AdminListPresenter presenter;
    private List<Incidences> incidences;
    private AdminListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_list);

        presenter = new AdminListPresenter(this,this);

        initializeReciclerView();
    }

    /**
     * Inicializa el ReciclerView
     */
    private void initializeReciclerView() {

        incidences = new ArrayList<>();

        RecyclerView recyclerView = findViewById(R.id.rcview_admin_list_incidences);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new AdminListAdapter(this, incidences);
        recyclerView.setAdapter(adapter);
    }

    /**
     * Llamada al presenter
     */
    @Override
    protected void onResume() {
        super.onResume();
        //Llamada al metodo del presenter
        presenter.loadAllIncidences();
    }

    /**
     * recobe del presenter
     * @param incidencesList
     */
    @Override
    public void showIncidencesAdmin(List<Incidences> incidencesList) {
        incidences.clear();
        incidences.addAll(incidencesList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showSnackBar(String message) {
        if (message != null) {
            Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG);
            snackbar.show();
        }
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