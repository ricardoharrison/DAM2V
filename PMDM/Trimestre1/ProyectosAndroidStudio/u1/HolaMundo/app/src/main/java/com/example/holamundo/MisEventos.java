package com.example.holamundo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MisEventos extends AppCompatActivity {

    TextView tv;

    Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_eventos);

        tv = findViewById(R.id.idtvut01Salida);
        bt = findViewById(R.id.idbtut01Accion);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //tv.setText(R.string.messageOnClick);  línea para imprimir el recurso messageOnClick
                tv.append(getResources().getString(R.string.messageOnClick)); // esta línea añade (append) el contenido actual
                                                                            //del objeto 'tv' una vez más
            }
        });
    }
}