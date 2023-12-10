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
import android.widget.TextView;

import com.juansenen.gaticketapp.R;
import com.juansenen.gaticketapp.contract.AdminChatContract;
import com.juansenen.gaticketapp.domain.ChatRequest;
import com.juansenen.gaticketapp.presenter.AdminChatPresenter;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AdminChatActivity extends AppCompatActivity implements AdminChatContract.view {

    private AdminChatPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_chat);

        presenter = new AdminChatPresenter(this, this);
    }

    /**
     * Funcion enviar pregunta a chatGPT
     * @param view
     * @return
     */
    public void talkToChatGpt(View view){
        //Recuperamos el campo de la query
        EditText queryText = findViewById(R.id.editTextQuestion);
        String query = queryText.getText().toString();
        // Crear la solicitud para enviar a la API
        ChatRequest chatGptRequest = new ChatRequest();
        chatGptRequest.setModel("gpt-3.5-turbo");

        // Mensaje del sistema
        ChatRequest.MessageGpt systemMessage = new ChatRequest.MessageGpt();
        systemMessage.setRole("system");
        systemMessage.setContent("You are a helpful assistant.");

        // Mensaje del usuario
        ChatRequest.MessageGpt userMessage = new ChatRequest.MessageGpt();
        userMessage.setRole("user");
        userMessage.setContent(query);

        // Agregar mensajes a la solicitud
        chatGptRequest.setMessages(Arrays.asList(systemMessage, userMessage));

        // Establecer otros parámetros de la solicitud (temperatura, max_tokens, etc.) según sea necesario
        chatGptRequest.setTemperature(0.5);
        chatGptRequest.setMax_tokens(256);

        // Log para verificar que la solicitud se construyó correctamente
        Log.d("TAG", "Solicitud a la API: " + chatGptRequest);

        // Llamar al presentador para manejar la solicitud a la API
        presenter.questionToChat(chatGptRequest);
    }


    @Override
    public void showResponse(String response) {
        Log.d("TAG", "Activity showResponse: " + response);
        TextView responseText = findViewById(R.id.edittext_response);
        responseText.setText("");
        responseText.setText(response);
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
        }else if (item.getItemId() == R.id.admin_menu_chatbot) {
            Intent intent = new Intent(this, AdminChatActivity.class);
            startActivity(intent);
            finish();
        }

        return false;
    }


}