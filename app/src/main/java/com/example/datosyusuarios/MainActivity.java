package com.example.datosyusuarios;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private EditText editTextMessage;
    private TextView textViewMessage;
    private static final String FILENAME = "datosUsuarios.txt";

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

                    FileOutputStream fos = openFileOutput(FILENAME, MODE_PRIVATE);
                    OutputStreamWriter osw = new OutputStreamWriter(fos);
                    osw.write(message);
                    osw.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        Button buttonShow = findViewById(R.id.buttonShow);
        buttonShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    FileInputStream fis = openFileInput(FILENAME);
                    InputStreamReader isr = new InputStreamReader(fis);
                    BufferedReader br = new BufferedReader(isr);
                    StringBuilder sb = new StringBuilder();
                    String line;

                    while ((line = br.readLine()) != null) {
                        sb.append(line).append("\n");
                    }

                    br.close();
                    textViewMessage.setText(sb.toString());

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
