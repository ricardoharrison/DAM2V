package com.example.pokedex;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.pokedex.data.DetalleResponse;

public class DetalleActivity extends AppCompatActivity {
    TextView tipo1, tipo2, nombre, hp, atk, spAtk, def, spDef, speed;
    ImageView front, back;
    ImageButton exit;
    PokemonViewModel pm;
    LiveData<DetalleResponse> detalle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        Intent intent = getIntent();

        String nombreRecibido = intent.getStringExtra(MainActivity.NOMBRE);

        tipo1 = findViewById(R.id.tipo1);
        tipo2 = findViewById(R.id.tipo2);
        nombre = findViewById(R.id.pk_nombre);
        hp = findViewById(R.id.hp);
        atk = findViewById(R.id.atk);
        spAtk = findViewById(R.id.sp_atk);
        def = findViewById(R.id.def);
        spDef = findViewById(R.id.sp_def);
        speed = findViewById(R.id.speed);
        front = findViewById(R.id.front);
        back = findViewById(R.id.back);
        exit = findViewById(R.id.exit);

        pm = new ViewModelProvider(this).get(PokemonViewModel.class);
        pm.init();

        detalle = pm.getPokemonDetalleLiveData();

        detalle.observe(this,(dato) ->{
            if (dato.getSprites().getFrontDefault() != null) {
                Glide.with(this)
                        .load(dato.getSprites().getFrontDefault())
                        .into(front);
                Glide.with(this)
                        .load(dato.getSprites().getBack_default())
                        .into(back);
            }
            if (dato.getTypes().size() == 2) {
                tipo1.setText(dato.getTypes().get(0).getType().getName());
                tipo2.setText(dato.getTypes().get(1).getType().getName());
            }else {
                tipo1.setText(dato.getTypes().get(0).getType().getName());
                tipo2.setText("");
            }

            nombre.setText(dato.getName());

            hp.setText(String.valueOf(dato.getStats().get(0).getBaseStat()));
            atk.setText(String.valueOf(dato.getStats().get(1).getBaseStat()));
            def.setText(String.valueOf(dato.getStats().get(2).getBaseStat()));
            spAtk.setText(String.valueOf(dato.getStats().get(3).getBaseStat()));
            spDef.setText(String.valueOf(dato.getStats().get(4).getBaseStat()));
            speed.setText(String.valueOf(dato.getStats().get(5).getBaseStat()));
        });

        pm.llamadaDetalle(nombreRecibido);

        exit.setOnClickListener(v ->{
            finish();
        });
    }
}