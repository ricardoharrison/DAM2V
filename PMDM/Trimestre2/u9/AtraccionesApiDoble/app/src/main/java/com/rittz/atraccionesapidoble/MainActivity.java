package com.rittz.atraccionesapidoble;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Button button;
    EditText editText;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        editText = findViewById(R.id.editTextText);
        textView = findViewById(R.id.textView);

        button.setOnClickListener(v -> {
            if(!editText.getText().toString().trim().isEmpty()){
                int input = Integer.parseInt(editText.getText().toString());
                AtraccionService service = AtraccionService.getInstance();
                Call<List<Atraccion>> llamada = service.getRepo().getAtracciones();

                llamada.enqueue(new Callback<List<Atraccion>>() {
                    @Override
                    public void onResponse(Call<List<Atraccion>> call, Response<List<Atraccion>> response) {
                        if (response.isSuccessful()) {
                            List<Atraccion> atraccions = response.body();
                            if (atraccions != null) {
                                try{
                                    String url = atraccions.get(input - 1).url;
                                    makeSecondApiCall(url);
                                } catch (Exception e) {
                                    textView.setText("No existe este objeto");
                                }


                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Atraccion>> call, Throwable t) {
                        textView.setText("Error al buscar la información");
                    }
                });
            }
        });
    }

    private void makeSecondApiCall(String url) {
        AtraccionService service = AtraccionService.getInstance();
        Call<AtraccionInstance> llamada = service.getRepo().getAtraccionDetails(url);

        llamada.enqueue(new Callback<AtraccionInstance>() {
            @Override
            public void onResponse(Call<AtraccionInstance> call, Response<AtraccionInstance> response) {
                if (response.isSuccessful()) {
                    AtraccionInstance a = response.body();
                    if (a != null) {
                        textView.setText(a.toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<AtraccionInstance> call, Throwable t) {
                textView.setText("Error al buscar la información");
            }
        });
    }
}