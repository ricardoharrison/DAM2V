package com.rittz.fragmentscharacters;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MyFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment myFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyFragment newInstance(String param1, String param2) {
        MyFragment fragment = new MyFragment();
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

    public interface OnCharacterSelected {
        public void characterSelected(GameCharacters gameCharacter, MyFragment fragment);
    }

    OnCharacterSelected listener;

    ImageView imageViewChar1, imageViewChar2, imageViewChar3;

    ArrayList<ImageView> imageViewList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_my, container, false);

        imageViewChar1 = layout.findViewById(R.id.imageViewChar1);
        imageViewChar2 = layout.findViewById(R.id.imageViewChar2);
        imageViewChar3 = layout.findViewById(R.id.imageViewChar3);

        imageViewList.add(imageViewChar1);
        imageViewList.add(imageViewChar2);
        imageViewList.add(imageViewChar3);

        imageViewChar1.setImageResource(R.drawable.zombie);
        imageViewChar2.setImageResource(R.drawable.mage);
        imageViewChar3.setImageResource(R.drawable.elf);

        imageViewChar1.setTag(GameCharacters.ZOMBIE);
        imageViewChar2.setTag(GameCharacters.MAGE);
        imageViewChar3.setTag(GameCharacters.ELF);

        View.OnClickListener handler = (view) -> {
            ImageView myImageView = (ImageView) view;
            if (myImageView.getTag().equals(GameCharacters.ZOMBIE)) {
                listener.characterSelected(GameCharacters.ZOMBIE, this);
            } else if (myImageView.getTag().equals(GameCharacters.MAGE)) {
                listener.characterSelected(GameCharacters.MAGE, this);
            } else if (myImageView.getTag().equals(GameCharacters.ELF)) {
                listener.characterSelected(GameCharacters.ELF, this);
            }
        };

        imageViewChar1.setOnClickListener(handler);
        imageViewChar2.setOnClickListener(handler);
        imageViewChar3.setOnClickListener(handler);

        return layout;
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof OnCharacterSelected){
            listener = (OnCharacterSelected) context;
        } else {
            throw new ClassCastException("Must implement OnCharacterSelected interface");
        }
    }
}