package com.juansenen.gaticketapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.juansenen.gaticketapp.R;
import com.juansenen.gaticketapp.contract.UserDetailContract;
import com.juansenen.gaticketapp.domain.Messages;
import com.juansenen.gaticketapp.presenter.UserDetailPresenter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class UserDetailActivity extends AppCompatActivity implements UserDetailContract.view {

    private UserDetailPresenter presenter;
    private long incidenceId;
    private long userId;
    private String userTip, incidenceTheme, incidenceCommit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        Context context = this;
        //Recuperamos el Id del usuario
        SharedPreferences sharedPreferences = context.getSharedPreferences("UserData", Context.MODE_PRIVATE);
        userId = Integer.parseInt(sharedPreferences.getString("user_id", ""));
        //Recuperamos datos
        incidenceId = getIntent().getLongExtra("idIncidence",0);
        incidenceTheme = getIntent().getStringExtra("incidenceTheme");
        incidenceCommit = getIntent().getStringExtra("incidenceCommit");

        presenter = new UserDetailPresenter(this,this);

        showDataIncidences();

    }

    /**
     * Funcion muestra los datos de la incidencia
     */
    private void showDataIncidences() {
        TextView textTheme = findViewById(R.id.txt_user_detail_theme);
        TextView textCommit = findViewById(R.id.txt_user_commit);

        textTheme.setText(incidenceTheme);
        textCommit.setText(incidenceCommit);

        showMessages();
    }

    private void showMessages() {

        presenter.getUserMessages(incidenceId);
    }
    @Override
    public void showAllMessages(List<Messages> messages) {
        showMessagesIncidence(messages);
    }

    @Override
    public void sendMenssageOk() {
        showMessages();
    }

    //Funcion general mostar mensajes. Recorre Array entero
    public void showMessagesIncidence(List<Messages> messages) {
        TableLayout tableMessages = findViewById(R.id.table_user_messages);

        // Limpiar cualquier contenido existente en la tabla
        tableMessages.removeAllViews();

        // Agregar filas de mensajes
        for (Messages message : messages) {
            addMessageToTable(message, tableMessages);
        }
    }
    //Funcion añadir mensaje a la tabla
    private void addMessageToTable(Messages message, TableLayout tableLayout) {
        // Fila 1: Fecha
        addRowToTable("Fecha", message.getTimeMessage().toString(), tableLayout);

        // Fila 2: Emisor
        addRowToTable("Emisor", message.getEmisorMessage().getUserTip().toString(), tableLayout);

        if (message.getEmisorMessage().getUserId() == userId) {
            // Fila 3: Mensaje cambio color si el mensaje es del usuario
            addRowToTableWithColors("Mensaje", message.getMessageCommit().toString(), tableLayout, R.color.colorFondoMensaje, R.color.colorTextoMensaje);
        } else {
            addRowToTable("Mensaje", message.getMessageCommit().toString(), tableLayout);
        }
        // Añade una fila vacía como separador
        addEmptyRowToTable(tableLayout);
    }

    //Funcion para poner color a los mensajes
    private void addRowToTableWithColors(String key, String value, TableLayout tableLayout, int backgroundColorResId, int textColorResId) {
        TableRow row = new TableRow(this);

        TextView keyTextView = new TextView(this);
        keyTextView.setText(key);
        keyTextView.setPadding(5, 5, 5, 5);

        TextView valueTextView = new TextView(this);
        valueTextView.setText(value);
        valueTextView.setPadding(5, 5, 5, 5);
        valueTextView.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));

        // Obtener colores de recursos
        int backgroundColor = getResources().getColor(backgroundColorResId);
        int textColor = getResources().getColor(textColorResId);

        // Aplicar colores al fondo y al texto del mensaje
        valueTextView.setBackgroundColor(backgroundColor);
        valueTextView.setTextColor(textColor);

        row.addView(keyTextView);
        row.addView(valueTextView);

        tableLayout.addView(row);
    }

    //Funcion añade a la tabla de mensajes 1 linea
    private void addRowToTable(String key, String value, TableLayout tableLayout) {
        TableRow row = new TableRow(this);

        TextView keyTextView = new TextView(this);
        keyTextView.setText(key);
        keyTextView.setPadding(5, 5, 5, 5);

        TextView valueTextView = new TextView(this);
        valueTextView.setText(value);
        valueTextView.setPadding(5, 5, 5, 5);
        valueTextView.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));


        row.addView(keyTextView);
        row.addView(valueTextView);

        tableLayout.addView(row);
    }

    //Fucion para añadir una linea vacia
    private void addEmptyRowToTable(TableLayout tableLayout) {
        // Añade una fila vacía como separador
        TableRow emptyRow = new TableRow(this);
        emptyRow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        tableLayout.addView(emptyRow);
    }


    /**
     * Boton enviar mensajes
     * @param view
     */
    public void userSendMessage(View view){
        //Recuperamos el cuerpo del mensaje
        TextInputEditText textoCommit = findViewById(R.id.editext_user_message);
        Messages messageBody = new Messages();
        String messageCommit = textoCommit.getText().toString();
        messageBody.setMessageCommit(messageCommit);
        messageBody.setTimeMessage(obtenerFechaHoraActual());
        Log.d("TAG", "Activity call presenter sendMessage " + incidenceId + " Fecha : " + messageBody.getTimeMessage());
        presenter.sendMessage(userId, incidenceId, messageBody);

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
    public static String obtenerFechaHoraActual() {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
        Date fechaHoraActual = new Date();

        return formato.format(fechaHoraActual);
    }



}