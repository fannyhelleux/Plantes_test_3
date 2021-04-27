package com.example.plantes_test_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button monBouton = (Button) findViewById(R.id.btn_newPlant);
        monBouton.setText("+");
        monBouton.setOnClickListener(new View.OnClickListener() {
            @Override
            // initialisation de l'action au clic
            public void onClick(View view) {
                visualiser_la_suite();
            }
        });
    }
    // création de l'action au clic
    private void visualiser_la_suite() {
        Intent intent = new Intent(this, NewPlantActivity.class);
        startActivity(intent);
    }
}

