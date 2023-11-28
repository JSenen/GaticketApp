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
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.juansenen.gaticketapp.R;
import com.juansenen.gaticketapp.contract.AdminDetailContract;
import com.juansenen.gaticketapp.domain.Department;
import com.juansenen.gaticketapp.domain.Incidences;
import com.juansenen.gaticketapp.domain.Messages;
import com.juansenen.gaticketapp.presenter.AdminDetailPresenter;

import java.util.List;

public class AdminDetailActivity extends AppCompatActivity implements AdminDetailContract.view {

    private AdminDetailPresenter presenter;
    private int adminId;
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
        long incidenceId = getIntent().getLongExtra("incidenceId",0);
        //recuperamos datos de la incidencia
        getIncidence(incidenceId);
    }

    private void getIncidence(long incidenceId) {
        presenter.getDataIncidence(incidenceId);
    }

    private void changeIncidenceStatus(Incidences incidenceBody) {
        incidenceBody.setIncidenceStatus("process");
        long incidenceId = incidenceBody.getIncidencesId();
        presenter.changeStatusIncidence(incidenceId,incidenceBody);

        //Añadimos a la incidencia
        incidenceBody.setAdminId(adminId);
        presenter.changeAdminIncidence(incidenceId, incidenceBody);

    }

    public void showDataIncidence(Incidences incidence) {

        //Recuperamos los datos de la incidencia
        TextView theme = findViewById(R.id.txt_detailIncidence_theme);
        TextView commit = findViewById(R.id.txt_detailIncidence_commit);
        TextView user = findViewById(R.id.txt_detailIncidence_usertip);


        theme.setText(incidence.getIncidenceTheme().toString());
        commit.setText(incidence.getIncidenceCommit().toString());
        user.setText(incidence.getUser().getUserTip().toString());
        //Recuperar departamento usuario
        long userId = incidence.getUser().getUserId();
        getDepartment(userId);
        //Recuperar los mensajes
        Log.d("TAG","Activity  showDataIncidence() ---> recuperar mensajes");
        long incidenceId = incidence.getIncidencesId();
        getMessages(incidenceId);

        changeIncidenceStatus(incidence);

    }

    private void getMessages(long incidenceId) {
        Log.d("TAG", "Activity call presenter getMessages( " + incidenceId + " )");
       presenter.getMessages(incidenceId);
    }
    @Override
    public void showAllMessages(List<Messages> messagesList) {
        showMessagesIncidence((messagesList));
    }

    public void showMessagesIncidence(List<Messages> messages) {
        Log.d("Messages List size", String.valueOf(messages.size()));
        //Recuperamos la tabla
        TableLayout tableMessages = findViewById(R.id.table_messages);
        for (Messages message: messages){
            addRowToTable("Emisor", message.getEmisorMessage().getUserTip().toString(), tableMessages);
            addRowToTable("Mensaje", message.getMessageCommit().toString(), tableMessages);
            addRowToTable("Tiempo del Mensaje", message.getTimeMessage().toString(), tableMessages);

            // Añade una fila vacía como separador )
            addEmptyRowToTable(tableMessages);
        }
    }
    // Función para añadir una fila a la tabla
    private void addRowToTable(String key, String value, TableLayout tableLayout) {
        TableRow row = new TableRow(this);

        TextView keyTextView = new TextView(this);
        keyTextView.setText(key);
        keyTextView.setPadding(5, 5, 5, 5);

        TextView valueTextView = new TextView(this);
        valueTextView.setText(value);
        valueTextView.setPadding(5, 5, 5, 5);

        row.addView(keyTextView);
        row.addView(valueTextView);

        tableLayout.addView(row);
    }

    // Función para añadir una fila vacía como separador entre mensajes
    private void addEmptyRowToTable(TableLayout tableLayout) {
        TableRow row = new TableRow(this);
        TextView emptyTextView = new TextView(this);
        emptyTextView.setText(""); //poner un espacio en blanco u otro carácter
        row.addView(emptyTextView);
        tableLayout.addView(row);
    }

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