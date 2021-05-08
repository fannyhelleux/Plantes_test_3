package com.example.plantes_test_2;

import androidx.annotation.RequiresApi;
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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

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

public class DetailPlanteActivity extends Activity {
    TextView txtf_namePlant, txtf_nameSci, txtf_freqArrosage, txtf_dateNextArrosage;
    int position_dbPlante;
    dataBase_manager dataBase_manager;
    ArrayList<Plante> arrayList;
    Plante plante;
    RatingBar ratingBar_lum;
    int nb_jours_interArrossage;
    AlarmManager alarmManager;
    String nom_plante;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_plante);
        dataBase_manager = new dataBase_manager(this);
        // initialisation des différents boutons
        Button btn_retour = (Button) findViewById(R.id.btn_retour);
        Button btn_suppPlante = (Button) findViewById(R.id.btn_suppPlante);
        Button btn_arrosagePlante = (Button) findViewById(R.id.btn_arrosagePlante);

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
        txtf_freqArrosage.setText(jours + " jours");
        ratingBar_lum.setRating(plante.get_lum());
        txtf_dateNextArrosage.setText(plante.get_dateNextArrosage());
        nb_jours_interArrossage = plante.get_nb_jours_interArrossage();
        // on va chercher le nom de la plante dans la class plante
        nom_plante = plante.get_nom();

        // Bouton de retour au main
        btn_retour.setOnClickListener(new View.OnClickListener() {
            @Override
            // initialisation de l'action au clic
            public void onClick(View view) {
                Intent intent_detail_main = new Intent(view.getContext(), MainActivity.class);
                startActivity(intent_detail_main);
            }
        });

        // Bouton de retour au main avec suppression de la plante de la BDD
        btn_suppPlante.setOnClickListener(new View.OnClickListener() {
            @Override
            // initialisation de l'action au clic
            public void onClick(View view) {
                dataBase_manager.supp_plante(plante.get_nom());
                Intent intent_detail_main = new Intent(view.getContext(), MainActivity.class);
                startActivity(intent_detail_main);
            }
        });

        btn_arrosagePlante.setOnClickListener(new View.OnClickListener() {
            @Override
            // initialisation de l'action au clic
            public void onClick(View view) {


                // initialisation de l'alarm
                alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

                Intent notificationIntent = new Intent(view.getContext(), AlarmReceiver.class);
                PendingIntent broadcast = PendingIntent.getBroadcast(view.getContext(), 100, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.SECOND, 5);
                alarmManager.setExact(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), broadcast);


                // ShowNotification("ton appli pref","arroser votre "+nom_plante+"dans "+nb_jours_interArrossage+" jours");


                /*
                // Récuparration de la date du jour qui sera considéré comme le premier arrosage
                Calendar calendar = Calendar.getInstance();
                // Ajout des jours jusqu'au prochain arrosage
                calendar.add(Calendar.DAY_OF_MONTH, nb_jours_interArrossage);
                // conversion de la date en chaine de caractere
                DateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY");
                String date = dateFormat.format(calendar.getTime());
                plante.set_dateNextArrosage(date);
                */

            }
        });
    }
    //méthode qui fait apparaitre la notification
   /* private void ShowNotification(String title , String msg) {

        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel("ID",
                    "name",
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription("Desc");
            notificationManager.createNotificationChannel(notificationChannel);
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"ID")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title) // titre de la notif
                .setContentText(msg)    // contenant de la notif
                .setAutoCancel(true); // supprime la notif quand on l'a touche

        Intent intent = new Intent(this,MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this,0,intent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pi);
        notificationManager.notify(0,builder.build());

        /*AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        //alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pi);
        long timeAtButtonClick = System.currentTimeMillis();
        long nb_de_jour = 1000*10;

        alarmManager.set(AlarmManager.RTC_WAKEUP,
                timeAtButtonClick + nb_de_jour,
                pi);*/

    //Calendar calendar = Calendar.getInstance();
    //calendar.set(Calendar.HOUR_OF_DAY,19);
    //calendar.set(Calendar.MINUTE,1);


}

