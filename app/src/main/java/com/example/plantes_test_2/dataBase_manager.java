package com.example.plantes_test_2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class dataBase_manager extends SQLiteOpenHelper {

    private static final int version_dataBase = 1;
    private static final String nom_dataBase = "plant_dataBase";
    public static final String Table_Plante = "Plante";
    public static final String Colonne_nom = "nom";
    public static final String Colonne_nomSci = "nomSci";
    public static final String Colonne_nb_jours_interArrossage = "nb_jours_interArrossage";
    public static final String Colonne_lum = "lum";
    public static final String Colonne_dateNextArrosage = "dateNextArrosage";


    public dataBase_manager(Context context) {
        super(context, "plant_dataBase", null, 1);
    }


    @Override
    //creation de la base de donnée
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS plante(nom TEXT PRIMARY KEY,nomSci TEXT,nb_jours_interArrossage INTEGER NOT NULL,lum FLOAT,dateNextArrosage TEXT)");
        // Valeur test de la bdd qui servira également de titre des colonnes pour la Listview
        db.execSQL("insert into  plante(nom,nomSci ,nb_jours_interArrossage ,lum,dateNextArrosage) values ('Nom :','micheld',2,0.2,'Date :')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Plante");
        onCreate(db);
    }

    // fonction de conversion de la base de donnée en une liste de plante
    public ArrayList<Plante> recupData() {
        ArrayList<Plante> arrayList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Plante", null);

        while (cursor.moveToNext()) {
            String nom = cursor.getString(0);
            String nomSci = cursor.getString(1);
            int nb_jours_interArrossage = cursor.getInt(2);
            float lum = cursor.getFloat(3);
            String dateNextArrosage = cursor.getString(4);

            Plante plante = new Plante(nom, nomSci, nb_jours_interArrossage, lum);
            arrayList.add(plante);

        }
        return arrayList;
    }


    // definition de l'ajout d'une plante
    public void ajouter_plante(Plante plante) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Colonne_nom, plante.get_nom());
        contentValues.put(Colonne_nomSci, plante.get_nomSci());
        contentValues.put(Colonne_nb_jours_interArrossage, plante.get_nb_jours_interArrossage());
        contentValues.put(Colonne_lum, plante.get_lum());
        contentValues.put(Colonne_dateNextArrosage, plante.get_dateNextArrosage());

        db.insert(Table_Plante, null, contentValues);
    }

    public void supp_plante(String nom) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM Plante WHERE nom \"" + nom + "\"");
    }
}
