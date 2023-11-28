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
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.juansenen.gaticketapp.R;
import com.juansenen.gaticketapp.contract.AdminDetailContract;
import com.juansenen.gaticketapp.domain.Department;
import com.juansenen.gaticketapp.domain.Incidences;
import com.juansenen.gaticketapp.domain.Messages;
import com.juansenen.gaticketapp.presenter.AdminDetailPresenter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AdminDetailActivity extends AppCompatActivity implements AdminDetailContract.view {

    private AdminDetailPresenter presenter;
    private int adminId;
    private long incidenceId;
    private long userId;
    private Button butSendMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_detail);

        Context context = this;
        //Recuperamos el Id del administrador
        SharedPreferences sharedPreferences = context.getSharedPreferences("UserData", Context.MODE_PRIVATE);
        adminId = Integer.parseInt(sharedPreferences.getString("user_id",""));


        presenter = new AdminDetailPresenter(this, this);
        //recojemos el Id de la incidencia
        incidenceId = getIntent().getLongExtra("incidenceId",0);
        //recuperamos datos de la incidencia
        getIncidence(incidenceId);
    }
    //Funcion obtener incidencias
    private void getIncidence(long incidenceId) {
        presenter.getDataIncidence(incidenceId);
    }
    //Funcion cambiarel estado de la incidencia a en proceso
    private void changeIncidenceStatus(Incidences incidenceBody) {
        incidenceBody.setIncidenceStatus("process");
        long incidenceId = incidenceBody.getIncidencesId();
        presenter.changeStatusIncidence(incidenceId,incidenceBody);

        //Añadimos a la incidencia
        incidenceBody.setAdminId(adminId);
        presenter.changeAdminIncidence(incidenceId, incidenceBody);

    }
    //Funcion mostrar los datos de la incidencia
    public void showDataIncidence(Incidences incidence) {

        //Recuperamos los datos de la incidencia
        TextView theme = findViewById(R.id.txt_detailIncidence_theme);
        TextView commit = findViewById(R.id.txt_detailIncidence_commit);
        TextView user = findViewById(R.id.txt_detailIncidence_usertip);


        theme.setText(incidence.getIncidenceTheme().toString());
        commit.setText(incidence.getIncidenceCommit().toString());
        user.setText(incidence.getUser().getUserTip().toString());
        //Recuperar departamento usuario
        userId = incidence.getUser().getUserId();
        getDepartment(userId);
        //Recuperar los mensajes
        Log.d("TAG","Activity  showDataIncidence() ---> recuperar mensajes");
        long incidenceId = incidence.getIncidencesId();
        getMessages(incidenceId);

        changeIncidenceStatus(incidence);

    }
    //Funcion obtener mensajes
    private void getMessages(long incidenceId) {
        Log.d("TAG", "Activity call presenter getMessages( " + incidenceId + " )");
       presenter.getMessages(incidenceId);
    }
    //Funcion mostar los mensajes
    @Override
    public void showAllMessages(List<Messages> messagesList) {
        showMessagesIncidence((messagesList));
    }

    @Override
    public void messageSendOk() {
        getMessages(incidenceId);
    }

    //Funcion añadir mensaje a la tabla
    private void addMessageToTable(Messages message, TableLayout tableLayout) {
        // Fila 1: Fecha
        addRowToTable("Fecha", message.getTimeMessage().toString(), tableLayout);

        // Fila 2: Emisor
        addRowToTable("Emisor", message.getEmisorMessage().getUserTip().toString(), tableLayout);

        if (message.getEmisorMessage().getUserId() == adminId) {
            // Fila 3: Mensaje cambio color si el mensaje es del admin
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
    //Funcion general mostar mensajes. Recorre todo el array recibido
    public void showMessagesIncidence(List<Messages> messages) {
        TableLayout tableMessages = findViewById(R.id.table_messages);

        // Limpiar cualquier contenido existente en la tabla
        tableMessages.removeAllViews();

        // Agregar filas de mensajes
        for (Messages message : messages) {
            addMessageToTable(message, tableMessages);
        }
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
    //Funcion para mostrar el Departamento
    @Override
    public void showDataDepartment(Department department) {
        TextView departmentUser = findViewById(R.id.txt_detailIncidence_department);
        TextView phone = findViewById(R.id.txt_detailIncidence_phone);

        departmentUser.setText(department.getDepartmentName().toString());
        phone.setText(department.getDepartmentPhone().toString());
    }

    private void getDepartment(long userId) {
        presenter.requestDepartmentUser(userId);
    }

    /**
     * Boton enviar mensaje
     * @param view
     */
    public void sendMessage(View view){
        //Recuperamos el cuerpo del mensaje
        TextInputEditText textoCommit = findViewById(R.id.edtxt_mensajer);
        Messages messageBody = new Messages();
        String messageCommit = textoCommit.getText().toString();
        messageBody.setMessageCommit(messageCommit);
        messageBody.setTimeMessage(obtenerFechaHoraActual());
        Log.d("TAG","Activity call presenter sendMessage "+ incidenceId + " Fecha : "+ messageBody.getTimeMessage());
        presenter.sendMessage(adminId, incidenceId, messageBody);

    }
    public static String obtenerFechaHoraActual() {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
        Date fechaHoraActual = new Date();

        return formato.format(fechaHoraActual);
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