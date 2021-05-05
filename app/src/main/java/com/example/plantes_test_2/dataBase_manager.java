package com.example.plantes_test_2;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class dataBase_manager extends SQLiteOpenHelper {

    private static final int version_dataBase = 1;
    private static final String nom_dataBase = "plant_dataBase";
    public static final String Table_Plante = "Plante";
    public static final String Colonne_nom = "nom";
    public static final String Colonne_nomSci = "nomSci";
    public static final String Colonne_nb_jours_interArrossage = "nb_jours_interArrossage";
    public static final String Colonne_lum = "lum";
    public static final String Colonne_dateNextArrosage = "dateNextArrosage";


    public dataBase_manager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, nom_dataBase, factory, version_dataBase);
    }

    @Override
    //creation de la base de donnée
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS plante( nom text PRIMARY KEY, nomSci text, nb_jours_interArrossage INTEGER NOT NULL, lum INTEGER, dateNextArrosage Date);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Plante");
        onCreate(db);
    }

    public void ajouter_pLante(Plante plante) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("insert into plante (nom, nom_sci, nb_jours_interArrossage, lum,dateNextArrosage) values (" + plante.get_nom() + ", " + plante.get_nomSci() + ", " + plante.get_nb_jours_interArrossage() + ", " + plante.get_lum() + ", " + plante.get_dateNextArrosage() + " );");
        db.close();
    }
}
