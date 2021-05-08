package com.example.plantes_test_2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DetailPlanteActivity extends Activity {
    TextView txtf_namePlant, txtf_nameSci, txtf_freqArrosage, txtf_dateNextArrosage;
    int position_dbPlante;
    dataBase_manager dataBase_manager;
    ArrayList<Plante> arrayList;
    Plante plante;
    RatingBar ratingBar_lum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_plante);
        dataBase_manager = new dataBase_manager(this);
        Button btn_retour = (Button) findViewById(R.id.btn_retour);


        //definition des différents champs à modifier en fonction de la plante sélectionnée
        txtf_namePlant = (TextView) findViewById(R.id.txtf_namePlant);
        txtf_nameSci = (TextView) findViewById(R.id.txtf_nameSci);
        txtf_freqArrosage = (TextView) findViewById(R.id.txtf_freqArrosage);
        ratingBar_lum = (RatingBar) findViewById(R.id.ratingBar_lum);
        txtf_dateNextArrosage = (TextView) findViewById(R.id.txtf_dateNextArrosage);

        String position = getIntent().getStringExtra("position");
        position_dbPlante = Integer.parseInt(position);

        arrayList = dataBase_manager.recupData();
        plante = arrayList.get(position_dbPlante);
        String jours = String.valueOf(plante.get_nb_jours_interArrossage());

        txtf_namePlant.setText(plante.get_nom());
        txtf_nameSci.setText(plante.get_nomSci());
        txtf_freqArrosage.setText(jours);
        ratingBar_lum.setRating(plante.get_lum());
        txtf_dateNextArrosage.setText(plante.get_dateNextArrosage());

        // Bouton de retour au main
        btn_retour.setOnClickListener(new View.OnClickListener() {
            @Override
            // initialisation de l'action au clic
            public void onClick(View view) {
                Intent intent_detail_main = new Intent(view.getContext(), MainActivity.class);
                startActivity(intent_detail_main);
            }
        });
    }
}