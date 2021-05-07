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
        db.execSQL("CREATE TABLE IF NOT EXISTS plante( nom text PRIMARY KEY, nomSci text, nb_jours_interArrossage INTEGER NOT NULL, lum float, dateNextArrosage text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Plante");
        onCreate(db);
    }

    public ArrayList<Plante> recupData() {
        ArrayList<Plante> arrayList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Plante", null);

        while (cursor.moveToNext()) {
            String nom = cursor.getString(0);
            String nomSci = cursor.getString(1);
            int nb_jours_interArrossage = cursor.getInt(3);
            float lum = cursor.getFloat(4);
            String dateNextArrosage = cursor.getString(5);

            Plante plante = new Plante(nom, nomSci, nb_jours_interArrossage, lum);
            arrayList.add(plante);

        }
        return arrayList;
    }

    // definition de l'ajout d'une plante
    public boolean ajouter_pLante(Plante plante) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Colonne_nom, plante.get_nom());
        contentValues.put(Colonne_nomSci, plante.get_nomSci());
        contentValues.put(Colonne_nb_jours_interArrossage, plante.get_nb_jours_interArrossage());
        contentValues.put(Colonne_lum, plante.get_lum());
        contentValues.put(Colonne_dateNextArrosage, plante.get_dateNextArrosage());
        long test = db.insert("Plante", null, contentValues);
        db.close();

        if (test == -1) {
            return false;
        } else {
            return true;
        }

    }
}
