package com.juansenen.gaticketapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.material.snackbar.Snackbar;
import com.juansenen.gaticketapp.R;
import com.juansenen.gaticketapp.contract.UserContract;
import com.juansenen.gaticketapp.domain.Device;
import com.juansenen.gaticketapp.domain.Incidences;
import com.juansenen.gaticketapp.presenter.UserPresenter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class UserMainActivity extends AppCompatActivity implements UserContract.view, UserContract.DeviceCallback {

    private UserPresenter presenter;
    private Context context;
    private Incidences incidenceBody;
    private String selectedOption, selectedText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);

        //texto en action bar
        getSupportActionBar().setTitle(R.string.zona_usuarios);

        //Recuperamos el select del usuario
        Spinner spinnerTipo = findViewById(R.id.ticket_spinner);
        // Creamos un ArrayAdapter con las opciones
        ArrayAdapter<String> adapterTipo = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        adapterTipo.add("---");
        adapterTipo.add("Numero de serie");
        adapterTipo.add("MAC");
        // Especificamos el diseño que se utilizará cuando aparezca la lista de opciones
        adapterTipo.setDropDownViewResource(com.google.android.material.R.layout.support_simple_spinner_dropdown_item);
        // Aplicar el adaptador al spinner
        spinnerTipo.setAdapter(adapterTipo);
        // Establecer el elemento vacío como seleccionado por defecto
        spinnerTipo.setSelection(0);

        //Listener para saber la opción marcada
        spinnerTipo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedOption = (String) parentView.getItemAtPosition(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

                spinnerTipo.setSelection(0);
                selectedOption = "---";

            }
        });

    }
    private void callApiToFindDevice(String selectedOption, String selectedText) {
        presenter = new UserPresenter(this,this,selectedOption, selectedText);
        presenter.searchDevice(selectedOption,selectedText);

    }

    @Override
    public void onDeviceFound(Device device) {

        addDeviceToIncidence(device);
        createIncidenceBody();
    }

    @Override
    public void onDeviceNotFound(Device device) {
        createIncidenceBody();
    }

    @Override
    public void onApiError(String errorMessage) {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), errorMessage, Snackbar.LENGTH_LONG);
        snackbar.show();
    }


    /**
     * Boton añadir incidencia
     */
    public void addIncidenceButton(View view){
        if (!selectedOption.equals("---")){
            Log.d("TAG","Selected option = "+selectedOption);
            selectedText = ((EditText) findViewById(R.id.editxt_data)).getText().toString();
            Log.d("TAG","Selected text " + selectedText);
            callApiToFindDevice(selectedOption, selectedText);

        } else {
            Log.d("TAG","Selected empty = "+selectedOption);
            callApiToFindDevice(selectedOption, selectedText);
        }
    }

    /**
     * Metodo crea la incidencia a grabar
     */
    public void createIncidenceBody(){
        Log.d("TAG", "createIncidenceBody: Creating incidence body");
        if (incidenceBody == null) {
            incidenceBody = new Incidences();
        }

        incidenceBody.setIncidenceTheme(((EditText) findViewById(R.id.editxt_theme)).getText().toString());
        incidenceBody.setIncidenceCommit(((EditText) findViewById(R.id.editxt_commit)).getText().toString());
        incidenceBody.setIncidenceDate(getCurrentFormattedDate());
        incidenceBody.setIncidenceDateFinish("");
        incidenceBody.setIncidenceStatus("active");
        saveIncidence(incidenceBody);
        Log.d("TAG", "createIncidenceBody: After saveIncidence");

    }
    private void saveIncidence(Incidences incidenceBody) {
        // Utilizamos el presenter para grabar la incidencia con la lógica correspondiente.
        if (presenter != null) {
            presenter.createIncidence(incidenceBody);
        }
    }
    /**
     * Método para añadir el dispositivo a la incidenciaBody.
     */
    private void addDeviceToIncidence(Device device) {
        if (incidenceBody == null) {
            incidenceBody = new Incidences();
        }
        incidenceBody.setDevice(device);

    }

    /**
     * Formateador de la fecha
     */
    private String getCurrentFormattedDate() {
        // Obtener la fecha actual
        Date currentDate = new Date();

        // Formato deseado para la fecha
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

        // Formatear la fecha actual
        return outputFormat.format(currentDate);
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

    @Override
    public void showSnackBar(String message) {
        if (message != null) {
            Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG);
            snackbar.show();
        }
    }
}