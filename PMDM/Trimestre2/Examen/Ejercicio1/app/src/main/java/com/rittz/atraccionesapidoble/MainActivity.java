package com.rittz.atraccionesapidoble;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Button button;
    Spinner spinner;
    RecyclerView recyclerView;
    Adapter adapter;
    ProgressBar progressBar;
    TextView textViewError, textViewCantidad, textViewImporte, textViewLabel1, textViewLabel2;
    final String BTC = "Bitcoin", ETH = "Ethereum";
    final int BTC_POSITION = 0, ETH_POSITION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        spinner = findViewById(R.id.spinner);
        recyclerView = findViewById(R.id.recyclerView);
        textViewError = findViewById(R.id.textViewError);
        textViewCantidad = findViewById(R.id.textViewCantidad);
        textViewImporte = findViewById(R.id.textViewImporte);
        textViewLabel1 = findViewById(R.id.textViewLabel1);
        textViewLabel2 = findViewById(R.id.textViewLabel2);
        progressBar = findViewById(R.id.progressBar);

        textViewLabel1.setVisibility(View.INVISIBLE);
        textViewLabel2.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.INVISIBLE);

        ArrayList<String> spinnerList = new ArrayList<>();
        spinnerList.add(BTC);
        spinnerList.add(ETH);


        ArrayAdapter<String> adapterContainer = new ArrayAdapter<>(this, com.google.android.material.R.layout.support_simple_spinner_dropdown_item, spinnerList);
        spinner.setAdapter(adapterContainer);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);




        button.setOnClickListener(v -> {
            button.setEnabled(false);
            progressBar.setVisibility(View.VISIBLE);
            textViewError.setText("");
            CryptoService service = CryptoService.getInstance();
            Call<List<Crypto>> llamada = service.getRepo().getCrypto();

                llamada.enqueue(new Callback<List<Crypto>>() {
                    @Override
                public void onResponse(Call<List<Crypto>> call, Response<List<Crypto>> response) {
                    if (response.isSuccessful()) {
                        List<Crypto> cryptos = response.body();
                        Crypto selectedCrypto = null;
                        if (cryptos != null) {


                            if(spinner.getSelectedItem().toString().equals(BTC)){
                                selectedCrypto = cryptos.get(BTC_POSITION);
                                adapter = new Adapter((ArrayList) (selectedCrypto.getEstafados()), getApplicationContext());

                                // Set the adapter to the RecyclerViews
                                recyclerView.setAdapter(adapter);
                                textViewCantidad.setText("" + selectedCrypto.getEstafados().size());
                                textViewImporte.setText("" + selectedCrypto.getEuros());

                            }
                            if(spinner.getSelectedItem().equals(ETH)){
                                selectedCrypto = cryptos.get(ETH_POSITION);
                                adapter = new Adapter((ArrayList) (selectedCrypto.getEstafados()), getApplicationContext());

                                // Set the adapter to the RecyclerView
                                recyclerView.setAdapter(adapter);
                                textViewCantidad.setText("" + selectedCrypto.getEstafados().size());
                                textViewImporte.setText("" + selectedCrypto.getEuros());
                            }

                        }
                        textViewLabel1.setVisibility(View.VISIBLE);
                        textViewLabel2.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.INVISIBLE);
                        button.setEnabled(true);
                    }
                }

                @Override
                public void onFailure(Call<List<Crypto>> call, Throwable t) {
                    textViewError.setText("Error al buscar la información");
                    progressBar.setVisibility(View.INVISIBLE);
                    textViewLabel1.setVisibility(View.INVISIBLE);
                    textViewLabel2.setVisibility(View.INVISIBLE);
                    button.setEnabled(true);
                }
            });

        });
    }
}