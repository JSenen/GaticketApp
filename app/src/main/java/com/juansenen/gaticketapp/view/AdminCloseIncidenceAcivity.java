package com.juansenen.gaticketapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.juansenen.gaticketapp.R;
import com.juansenen.gaticketapp.contract.AdminCloseIncidenceContract;
import com.juansenen.gaticketapp.domain.Incidences;
import com.juansenen.gaticketapp.domain.IncidencesHistory;
import com.juansenen.gaticketapp.presenter.AdminCloseIncidencePresenter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AdminCloseIncidenceAcivity extends AppCompatActivity implements AdminCloseIncidenceContract.view {

    private AdminCloseIncidencePresenter presenter;
    private String adminId, incidenceId;
    private IncidencesHistory incidenceToSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_close_incidence_acivity);

        //Recuperamos los Id de administrador e inicdencia
        Intent intent = getIntent();
        incidenceId = String.valueOf(intent.getLongExtra("incidenceId",0));
        adminId = String.valueOf(intent.getIntExtra("adminId",0));

        incidenceToSave = new IncidencesHistory();
        presenter = new AdminCloseIncidencePresenter(this,this);
        Log.d("TAG","Activity call requestIncidence presenter id = "+ incidenceId);
        presenter.requestIncidence(incidenceId);
    }

    @Override
    public void getDataIncidence(Incidences incidence) {

        incidenceToSave.setHistoryTip(incidence.getUser().getUserTip());
        incidenceToSave.setHistoryAdmin(adminId);
        incidenceToSave.setHistoryCommit(incidence.getIncidenceCommit());
        incidenceToSave.setHistoryTheme(incidence.getIncidenceTheme());
        String fecha = obtenerFecha();
        incidenceToSave.setHistoryDateFinish(fecha);

    }

    @Override
    public void showSnackBar(String message) {
        Toast.makeText(this,message,Toast.LENGTH_LONG);
        Intent intent = new Intent(this, AdminListActivity.class);
        startActivity(intent); // Cambiamos de Activity
        finish(); // Cerramos la actual

    }

    public static String obtenerFecha() {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        Date fechaActual = new Date();

        return formato.format(fechaActual);
    }

    /**
     * Evento en el boton para grabar la incidencia
     * @param view
     */
    public void saveIncidence(View view){
        //Recuperamos la solucion aportada
        EditText textSolution = findViewById(R.id.editText_solution);
        String solution = textSolution.getText().toString();
        incidenceToSave.setHistorySolution(solution);
        saveIncidenceTohistory(incidenceToSave);
    }
    public void saveIncidenceTohistory(IncidencesHistory incidenceBody){
        //Grbaamos la incidencia
        Log.d("TAG","Activity call presenter saveIncidence to save "+incidenceBody);
        presenter.saveIncidence(incidenceBody);
        deleteIncidence(incidenceId);
    }

    private void deleteIncidence(String incidenceId) {
        presenter.deleteIncidence(incidenceId);
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

        }else if (item.getItemId() == R.id.admin_menu_history) {
            //Enviar a listados de historiales
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