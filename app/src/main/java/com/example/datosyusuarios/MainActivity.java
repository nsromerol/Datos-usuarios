package com.example.datosyusuarios;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private EditText editTextMessage;
    private TextView textViewMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextMessage = findViewById(R.id.editTextMessage);
        textViewMessage = findViewById(R.id.textViewMessage);

        Button buttonSave = findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = editTextMessage.getText().toString();

                try {
                    OutputStreamWriter fout = new OutputStreamWriter(openFileOutput("datosUsuarios.txt", Context.MODE_PRIVATE));
                    fout.write(message);
                    fout.close();
                    editTextMessage.setText("");
                } catch (Exception ex) {
                    Log.e("Ficheros", "Error al escribir fichero en memoria interna");
                }
            }
        });

        Button buttonShow = findViewById(R.id.buttonShow);
        buttonShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    BufferedReader fin = new BufferedReader(new InputStreamReader(openFileInput("datosUsuarios.txt")));
                    StringBuilder mensaje = new StringBuilder();
                    String linea;

                    while ((linea = fin.readLine()) != null) {
                        mensaje.append(linea).append("\n");
                    }

                    fin.close();
                    String mensajeCompleto = mensaje.toString();
                    textViewMessage.setText(mensajeCompleto);
                } catch (Exception ex) {
                    Log.e("Ficheros", "Error al leer fichero desde memoria interna");
                }
            }
        });
    }
}

