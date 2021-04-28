package com.example.plantes_test_2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NewPlantActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_plant);
        //définition du bouton d'envoi sur l'activité d'ajout
        Button btn_addPlant = (Button) findViewById(R.id.btn_addPlant);
        btn_addPlant.setText("AJOUTER");
        btn_addPlant.setOnClickListener(new View.OnClickListener() {
            @Override
            // initialisation de l'action au clic
            public void onClick(View view) {
                retour_mainActivity();
                ajouter_plante();
            }
        });
    }

    // création de l'action au clic
    private void retour_mainActivity() {
        Intent intent_newPlant_main = new Intent(this, MainActivity.class);
        startActivity(intent_newPlant_main);
    }

    // création de l'ajout de la plante a la bdd
    private void ajouter_plante() {

    }
}
