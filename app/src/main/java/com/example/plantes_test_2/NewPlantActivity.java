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

    EditText txtf_namePlant, txtf_nameSci, txtf_freqArrosage;
    RatingBar ratingBar_lum;
    dataBase_manager dataBase_manager;
    String txt_namePlant;
    String txt_nameSci;
    int nb_jour_interArrosage;
    float nb_lum;
    Toast toast;
    Plante plante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_plant);

        //définition du bouton d'envoi sur l'activité d'ajout
        Button btn_addPlant = (Button) findViewById(R.id.btn_addPlant);
        Button btn_retour = (Button) findViewById(R.id.btn_retour);
        dataBase_manager = new dataBase_manager(this);

        //definition des différents champs à remplir
        txtf_namePlant = (EditText) findViewById(R.id.txtf_namePlant);
        txtf_nameSci = (EditText) findViewById(R.id.txtf_nameSci);
        txtf_freqArrosage = (EditText) findViewById(R.id.txtf_freqArrosage);
        ratingBar_lum = (RatingBar) findViewById(R.id.ratingBar_lum);


        // Bouton de retour au main avec ajout de plante
        btn_addPlant.setOnClickListener(new View.OnClickListener() {

            @Override
            // initialisation de l'action au clic
            public void onClick(View view) {

                // Conversion des champs en type exploitable par la bdd
                txt_namePlant = txtf_namePlant.getText().toString();
                txt_nameSci = txtf_nameSci.getText().toString();
                String value = txtf_freqArrosage.getText().toString();
                nb_jour_interArrosage = Integer.parseInt(value);
                nb_lum = ratingBar_lum.getRating();

                ajouter_plante(txt_namePlant, txt_nameSci, nb_jour_interArrosage, nb_lum);
                retour_mainActivity();

            }
        });


        // Bouton de retour au main sans ajout de plante
        btn_retour.setOnClickListener(new View.OnClickListener() {
            @Override
            // initialisation de l'action au clic
            public void onClick(View view) {
                retour_mainActivity();
            }
        });

    }

    // retour à la page d'accueil
    private void retour_mainActivity() {
        Intent intent_newPlant_main = new Intent(this, MainActivity.class);
        startActivity(intent_newPlant_main);
    }

    private void ajouter_plante(String nom, String nomSci, int nb_jour, float lum) {
        plante = new Plante(nom, nomSci, nb_jour, lum);
        if (plante != null) {
            dataBase_manager.ajouter_plante(plante);
        }
    }
}
