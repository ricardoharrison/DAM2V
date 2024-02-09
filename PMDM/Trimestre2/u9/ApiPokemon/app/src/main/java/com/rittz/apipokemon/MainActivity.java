package com.rittz.apipokemon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Button buttonApiCall;
    EditText editTextInput;
    TextView textViewInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonApiCall = findViewById(R.id.buttonCallApi);
        editTextInput = findViewById(R.id.editTextInput);
        textViewInfo = findViewById(R.id.textViewInfo);

        buttonApiCall.setOnClickListener((v)->{

            PokemonService service = PokemonService.getInstance();
            Call<Pokemon> llamada =  service.getRepo().getPokemon(editTextInput.getText().toString().toLowerCase().trim());

            llamada.enqueue(new Callback<Pokemon>() {

                @Override
                public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                    Pokemon pokemon = response.body();
                    textViewInfo.setText(pokemon.toString());
                }

                @Override
                public void onFailure(Call<Pokemon> call, Throwable t) {
                    textViewInfo.setText("Error al buscar la información");
                }
            });

        });




    }
}