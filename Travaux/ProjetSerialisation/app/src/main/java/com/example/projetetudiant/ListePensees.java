package com.example.projetetudiant;

import android.content.Context;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Vector;

public class ListePensees {

    
    private Vector<String> lesPensees;
    private Context context;

   

    public void serialiserListe() {
          

                FileOutputStream fos = context.openFileOutput("fichier.ser", Context.MODE_PRIVATE);
       
    }

    public void recupererDuFichierDeSerialisation() {

     
            FileInputStream fis = context.openFileInput("test.ser");
    
        }





}
