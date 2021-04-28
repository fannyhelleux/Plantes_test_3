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
        btn_addPlant.setText("AJOUTER");

        //definition des différents champs à remplir
        EditText txtf_namePlant = (EditText) findViewById(R.id.txtf_namePlant);
        EditText txtf_nameSci = (EditText) findViewById(R.id.txtf_nameSci);
        EditText txtf_freqArrosage = (EditText) findViewById(R.id.txtf_freqArrosage);
        RatingBar ratingBar_lum = (RatingBar) findViewById(R.id.ratingBar_lum);

        // Conversion des champs en type exploitable par la bdd
        String txt_namePlant = String.valueOf(txtf_namePlant);
        String txt_nameSci = String.valueOf(txtf_nameSci);
        int nb_jour_interArrosage = Integer.valueOf(String.valueOf(txtf_freqArrosage));
        float nb_lum = ratingBar_lum.getRating();


        // définition et création de la BDD contenant l'essemble des données des plantes
        plant_dataBase = openOrCreateDatabase("donnees_plantes", MODE_PRIVATE, null);
        plant_dataBase.execSQL("CREATE TABLE IF NOT EXISTS plante(" +
                " nom text PRIMARY KEY," +
                " nom_sci text," +
                " nb_jours_interArrossage INTEGER NOT NULL," +
                " lum INTEGER NOT NULL);"

        );
        btn_addPlant.setOnClickListener(new View.OnClickListener() {
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

    // ajout de la plante a la bdd
    private void ajouter_plante(String nom_recup, String nom_sci_recup, int nb_jour_interArrosage_recup, float lum_recup) {
        plant_dataBase.execSQL("insert into plante (nom, nom_sci, nb_jours_interArrossage, lum) values (nom_recup, nom_sci_recup, nb_jour_interArrosage_recup, lum_recup );");
    }
}
