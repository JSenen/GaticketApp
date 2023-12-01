package com.juansenen.gaticketapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.juansenen.gaticketapp.R;
import com.juansenen.gaticketapp.adapter.UserListAdapter;
import com.juansenen.gaticketapp.contract.UserListContract;
import com.juansenen.gaticketapp.domain.Incidences;
import com.juansenen.gaticketapp.presenter.UserListPresenter;

import java.util.ArrayList;
import java.util.List;

public class UserListActivity extends AppCompatActivity implements UserListContract.view {

    private UserListPresenter presenter;
    private List<Incidences> incidences;
    private UserListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        presenter = new UserListPresenter(this,this);

        initializeReciclerView();

    }

    @Override
    protected void onResume() {
        super.onResume();

        presenter.loadAllIncidences();
    }

    /**
     * Inicializa el ReciclerView
     */
    private void initializeReciclerView() {

        incidences = new ArrayList<>();

        RecyclerView recyclerView = findViewById(R.id.rcview_see_incidences_user);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new UserListAdapter(this, incidences);
        recyclerView.setAdapter(adapter);
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
            //Grabar ticker
            Intent intent = new Intent(this,UserMainActivity.class);
            startActivity(intent);
            finish();
        } else if (item.getItemId() == R.id.item_usermenu_exit) {
            //Enviar a la pagina de login inicial
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent); // Cambiamos de Activity
            finish(); // Cerramos la actual

        }

        return false;
    }

    @Override
    public void showIncidencesUser(List<Incidences> incidencesList) {
        incidences.clear();
        incidences.addAll(incidencesList);
        adapter.notifyDataSetChanged();
    }
}