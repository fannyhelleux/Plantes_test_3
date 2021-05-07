package com.example.plantes_test_2;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

public class NewPlantActivity extends Activity {
    public SQLiteDatabase plant_dataBase;

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
                String txt_namePlant = String.valueOf(txtf_namePlant);
                String txt_nameSci = String.valueOf(txtf_nameSci);
                int nb_jour_interArrosage = Integer.parseInt(String.valueOf(txtf_freqArrosage));
                float nb_lum = ratingBar_lum.getRating();
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
}
