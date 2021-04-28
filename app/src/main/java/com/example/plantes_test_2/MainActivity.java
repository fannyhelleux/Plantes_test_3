package com.example.plantes_test_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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


    }
    // création de l'action au clic
    private void visualiser_la_suite() {
        Intent intent_main_newPlant = new Intent(this, NewPlantActivity.class);
        startActivity(intent_main_newPlant);
    }
}

