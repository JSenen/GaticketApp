package com.juansenen.gaticketapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.juansenen.gaticketapp.R;
import com.juansenen.gaticketapp.domain.DataToTransfer;

public class AdminHistoryDetail extends AppCompatActivity {

    private long historyId;
    private String theme, commit, solution, date, user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_history_detail);

        //Recogemos los datos de la incidencia archivada
        DataToTransfer data = DataToTransfer.getInstance();

        theme = data.getTheme();
        commit = data.getCommit();
        date = data.getDate();
        user = data.getUser();
        solution = data.getSolution();

        showDataOnActivity(theme,commit,user,date,solution);

    }

    private void showDataOnActivity(String theme, String commit, String user, String date, String solution) {

        TextView themeTxt = findViewById(R.id.txt_history_theme);
        TextView commitTxt = findViewById(R.id.txt_history_commit);
        TextView dateTxt = findViewById(R.id.txt_history_date);
        TextView solutionTxt = findViewById(R.id.txt_solution_history);

        themeTxt.setText(theme);
        commitTxt.setText(commit);
        dateTxt.setText(date);
        solutionTxt.setText(solution);
    }
    /**
     * Opciones menu Action bar
     *
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
            //Enviar a listados de historiales
            Intent intent = new Intent(this, AdminHistoryActivity.class);
            startActivity(intent);
            finish();
        }

        return false;
    }

}