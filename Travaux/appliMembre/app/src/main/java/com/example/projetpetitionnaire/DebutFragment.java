package com.example.projetpetitionnaire;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;


public class DebutFragment extends Fragment {



    EditText champNom;
    EditText champPrenom;
    RadioGroup groupe;

    public DebutFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup )inflater.inflate(R.layout.fragment_debut, container, false);
        champNom =rootView.findViewById(R.id.champNom);
        champPrenom = rootView.findViewById(R.id.champPrenom);
        groupe = rootView.findViewById(R.id.groupe);

        return rootView;
    }

    @Override
    public void onPause() {
        super.onPause();
        // à compléter
        ((ConteneurFragmentsActivity)getActivity()).getM().setNom(champNom.getText().toString());
        ((ConteneurFragmentsActivity)getActivity()).getM().setPrenom(champPrenom.getText().toString());
        ((ConteneurFragmentsActivity)getActivity()).getM().setObjectif(groupe.toString());





    }
}