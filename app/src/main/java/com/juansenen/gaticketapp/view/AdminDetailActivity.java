package com.juansenen.gaticketapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.juansenen.gaticketapp.R;

public class AdminDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_detail);

        //recojemos el Id de la incidencia
        long incidenceId = getIntent().getLongExtra("incidenceId",0);

    }
}