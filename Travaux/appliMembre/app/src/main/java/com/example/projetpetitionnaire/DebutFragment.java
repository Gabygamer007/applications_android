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
        Membre.Builder m = ((ConteneurFragmentsActivity)getActivity()).getM();
        m.setNom(champNom.getText().toString());
        m.setPrenom(champNom.getText().toString());
        int id = groupe.getCheckedRadioButtonId();
        if ( id == R.id.bPerdreDuPoids ) {
            m.setObjectif("perdre du poids");
        }
        else if ( id == R.id.bEtreMeilleureSante ) {
            m.setObjectif("etre en meilleure santé");
        }
        else {
            m.setObjectif("gagner de la masse musculaire");
        }





    }
}