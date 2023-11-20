package com.juansenen.gaticketapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.juansenen.gaticketapp.R;
import com.juansenen.gaticketapp.contract.AdminDetailContract;
import com.juansenen.gaticketapp.domain.Incidences;
import com.juansenen.gaticketapp.presenter.AdminDetailPresenter;

public class AdminDetailActivity extends AppCompatActivity implements AdminDetailContract.view {

    private AdminDetailPresenter presenter;
    private int adminId;
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

        theme.setText(incidence.getIncidenceTheme().toString());
        commit.setText(incidence.getIncidenceCommit().toString());

        changeIncidenceStatus(incidence);

    }
}