package com.example.plantes_test_2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.plantes_test_2.adaptateur.MonAdaptateur;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends Activity {

    // définition des différentes variables nécessaire dans l'activité
    MonAdaptateur monAdaptateur;
    ArrayList<Plante> arrayList;
    ListView ListView_plante;
    dataBase_manager dataBase_manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView_plante = (ListView) findViewById(R.id.ListView_plante);
        dataBase_manager = new dataBase_manager(this);
        arrayList = new ArrayList<>();
        dataToArrayList();

        //ajout de click vers activité dans listview


        //définition du bouton d'envoi sur l'activité d'ajout
        Button btn_newPlant = (Button) findViewById(R.id.btn_newPlant);
        btn_newPlant.setText("+");

        btn_newPlant.setOnClickListener(new View.OnClickListener() {
            @Override
            // initialisation de l'action au clic
            public void onClick(View view) {
                visualiser_la_suite();
            }
        });

        ListView_plante.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent_main_ficheTech = new Intent(this, fiche_technique_plante.class);
                intent_main_ficheTech.putExtra("position", position);
                startActivity(intent_main_ficheTech);
            }
        });


    }

    // fonction de transfert des données de la base de données à une liste de plante
    private void dataToArrayList() {
        arrayList = dataBase_manager.recupData();
        monAdaptateur = new MonAdaptateur(this, arrayList);
        ListView_plante.setAdapter(monAdaptateur);
        monAdaptateur.notifyDataSetChanged();

    }

    // création de l'action au clic
    private void visualiser_la_suite() {
        Intent intent_main_newPlant = new Intent(this, NewPlantActivity.class);
        startActivity(intent_main_newPlant);
    }
}

