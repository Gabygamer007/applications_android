package com.example.examen2;

import java.util.Hashtable;
import java.util.Vector;

public class Stock {



   public static Vector<Hashtable<String, String>> retournerListe()
   {
       Vector<Hashtable<String, String>> inventaire = new Vector();
       Hashtable<String, String> h = new Hashtable();
       h.put("nom", "PackTourDeFrance");
       h.put("prix", "329.99");
       inventaire.add(h);

       h = new Hashtable();
       h.put("nom", "Adaptateur Champion");
       h.put("prix", "129.99");
       inventaire.add(h);

       h = new Hashtable();
       h.put("nom", "Garantie Prolongee");
       h.put("prix", "149.99");
       inventaire.add(h);

       return inventaire;
   }



}
