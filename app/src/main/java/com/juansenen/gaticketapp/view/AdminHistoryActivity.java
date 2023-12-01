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
import com.juansenen.gaticketapp.adapter.AdminHistoryAdapter;
import com.juansenen.gaticketapp.adapter.AdminListAdapter;
import com.juansenen.gaticketapp.contract.AdminHistoryContract;
import com.juansenen.gaticketapp.domain.IncidencesHistory;
import com.juansenen.gaticketapp.presenter.AdminHistoryPresenter;

import java.util.ArrayList;
import java.util.List;

public class AdminHistoryActivity extends AppCompatActivity implements AdminHistoryContract.view {

    private AdminHistoryPresenter presenter;
    private List<IncidencesHistory> incidencesHistoryList;
    private AdminHistoryAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_hisotry);

        presenter = new AdminHistoryPresenter(this,this);
        initializeRecicler();
    }

    private void initializeRecicler() {
        incidencesHistoryList = new ArrayList<>();

        RecyclerView recyclerView = findViewById(R.id.rcview_history);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new AdminHistoryAdapter(this, incidencesHistoryList);
        recyclerView.setAdapter(adapter);
    }
    /**
     * Llamada al presenter
     */
    @Override
    protected void onResume() {
        super.onResume();
        //Llamada al metodo del presenter
        presenter.loadAllHistory();
    }

    @Override
    public void showIncidencesList(List<IncidencesHistory> historyList) {
        incidencesHistoryList.clear();
        incidencesHistoryList.addAll(historyList);
        adapter.notifyDataSetChanged();
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

        } else if (item.getItemId() == R.id.admin_menu_history) {
            Intent intent = new Intent(this, AdminHistoryActivity.class);
            startActivity(intent);
            finish();
        }else if (item.getItemId() == R.id.admin_menu_chatbot) {
            Intent intent = new Intent(this, AdminChatActivity.class);
            startActivity(intent);
            finish();
        }

        return false;
    }
}