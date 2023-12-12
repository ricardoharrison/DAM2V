package com.rittz.citas;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MyFragment extends Fragment {

    private TextView tvFinalMsg;

    // ...

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        tvFinalMsg = view.findViewById(R.id.textViewFinalMsg);
        return view;
    }

    public void updateFinalMsg(String message) {
        if (tvFinalMsg != null) {
            tvFinalMsg.setText(message);
        }
    }
}

