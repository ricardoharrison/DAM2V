package com.rittz.myapplication;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentCuenta#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentCuenta extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    static final int INITIAL_COUNT = 0;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentCuenta() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentCuenta.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentCuenta newInstance(String param1, String param2) {
        FragmentCuenta fragment = new FragmentCuenta();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    TextView textViewTipo, textViewCuenta;
    Button buttonSumar, buttonRestar;

    int total;

    boolean isViewCreated = false;

    public interface OnPersonAdded{
        void addPerson(FragmentCuenta fragment, int total);
    }

    OnPersonAdded listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View layout = inflater.inflate(R.layout.fragment_cuenta, container, false);

        textViewTipo = layout.findViewById(R.id.textViewTipo);
        textViewCuenta = layout.findViewById(R.id.textViewCuenta);

        buttonSumar = layout.findViewById(R.id.buttonMas);
        buttonRestar = layout.findViewById(R.id.buttonMenos);

        textViewCuenta.setText(String.valueOf(INITIAL_COUNT));

        buttonSumar.setOnClickListener(v -> {
            total = Integer.parseInt(textViewCuenta.getText().toString());
            total++;
            ponerPuntos();
        });

        buttonRestar.setOnClickListener(v -> {
            total = Integer.parseInt(textViewCuenta.getText().toString());
            if(total > 0){
                total--;
            }
            listener.addPerson(this, total);
            ponerPuntos();
        });

        isViewCreated = true;


        return layout;

    }

    private void ponerPuntos() {
        textViewCuenta.setText("");
        for(int i = 0; i < total; i++){
            textViewCuenta.setText(textViewCuenta.getText() + ".");
        }
    }

    void setName(String name){
        if(isViewCreated && textViewTipo != null){
            textViewTipo.setText(name);
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof OnPersonAdded){
            listener = (OnPersonAdded) context;
        } else {
            throw new ClassCastException("Debes implementar la interfaz OnPersonAdded");
        }
    }
}