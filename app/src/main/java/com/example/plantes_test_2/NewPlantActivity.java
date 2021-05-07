package com.example.plantes_test_2;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

public class NewPlantActivity extends Activity {

    dataBase_manager dataBase_manager;
    String txt_namePlant;
    String txt_nameSci;
    int nb_jour_interArrosage;
    float nb_lum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_plant);

        //définition du bouton d'envoi sur l'activité d'ajout
        Button btn_addPlant = (Button) findViewById(R.id.btn_addPlant);
        Button btn_retour = (Button) findViewById(R.id.btn_retour);

        //definition des différents champs à remplir
        EditText txtf_namePlant = (EditText) findViewById(R.id.txtf_namePlant);
        EditText txtf_nameSci = (EditText) findViewById(R.id.txtf_nameSci);
        EditText txtf_freqArrosage = (EditText) findViewById(R.id.txtf_freqArrosage);
        RatingBar ratingBar_lum = (RatingBar) findViewById(R.id.ratingBar_lum);



        // Bouton de retour au main avec ajout de plante
        btn_addPlant.setOnClickListener(new View.OnClickListener() {

            @Override
            // initialisation de l'action au clic
            public void onClick(View view) {
                retour_mainActivity();

                // Conversion des champs en type exploitable par la bdd
                txt_namePlant = String.valueOf(txtf_namePlant);
                txt_nameSci = String.valueOf(txtf_nameSci);
                String value = txtf_freqArrosage.getText().toString();
                nb_jour_interArrosage = Integer.parseInt(value);
                nb_lum = ratingBar_lum.getRating();
            }
        });


        // Bouton de retour au main sans ajout de plante
        btn_retour.setOnClickListener(new View.OnClickListener() {
            @Override
            // initialisation de l'action au clic
            public void onClick(View view) {
                retour_mainActivity();
                ajouter_plante(txt_namePlant, txt_nameSci, nb_jour_interArrosage, nb_lum);
            }
        });

    }

    // retour à la page d'accueil
    private void retour_mainActivity() {
        Intent intent_newPlant_main = new Intent(this, MainActivity.class);
        startActivity(intent_newPlant_main);
    }

    private void ajouter_plante(String nom, String nomSci, int nb_jour, float lum) {
        Plante plante = new Plante(nom, nomSci, nb_jour, lum);
        boolean test = dataBase_manager.ajouter_pLante(plante);
        if (test) {
            Toast.makeText(getApplicationContext(), "Nice bv batard", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "VA TE FAIRE ENCULER SALE PUTE", Toast.LENGTH_LONG).show();
        }
    }
}
