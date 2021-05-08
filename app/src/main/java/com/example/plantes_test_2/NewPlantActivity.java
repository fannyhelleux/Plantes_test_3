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

import android.app.Activity;
import android.app.AlarmManager;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Calendar;
public class NewPlantActivity extends Activity {

    EditText txtf_namePlant, txtf_nameSci, txtf_freqArrosage;
    RatingBar ratingBar_lum;
    dataBase_manager dataBase_manager;
    String txt_namePlant;
    String txt_nameSci;
    int nb_jour_interArrosage;
    float nb_lum;
    Plante plante;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
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

// initialisation de l'alarm
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent notificationIntent = new Intent(this, AlarmReceiver.class);
        PendingIntent broadcast = PendingIntent.getBroadcast(this, 100, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, 5);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), broadcast);
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
                ajouter_plante(txt_namePlant, txt_nameSci, nb_jour_interArrosage, nb_lum);
                retour_mainActivity();
                String nom_plante = plante.get_nom();
                ShowNotification("ton appli pref", "arroser votre " + nom_plante + "dans " + nb_jour_interArrosage + " jours");
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

    //méthode qui fait apparaitre la notification
    private void ShowNotification(String title, String msg) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel("ID",
                    "name",
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription("Desc");
            notificationManager.createNotificationChannel(notificationChannel);
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "ID")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(msg)
                .setAutoCancel(true);
// changer ici mainactivity pour l'afficher dans l'act 3
        Intent intent = new Intent(this, DetailPlanteActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pi);
        notificationManager.notify(0, builder.build());

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 19);
        calendar.set(Calendar.MINUTE, 1);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pi);
       /* long timeAtButtonClick = System.currentTimeMillis();
        long nb_de_jour = 1000*100;

        alarmManager.set(AlarmManager.RTC_WAKEUP,
        timeAtButtonClick + nb_de_jour,
                pi); */
    }
    // retour à la page d'accueil
    private void retour_mainActivity() {
        Intent intent_newPlant_main = new Intent(this, MainActivity.class);
        startActivity(intent_newPlant_main);
    }

    private void ajouter_plante(String nom, String nomSci, int nb_jour, float lum) {
        plante = new Plante(nom, nomSci, nb_jour, lum);
        dataBase_manager.ajouter_plante(plante);
    }
}
