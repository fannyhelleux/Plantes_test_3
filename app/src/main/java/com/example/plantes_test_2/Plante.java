package com.example.plantes_test_2;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Plante {

    // définition des
    private String _nom;
    private String _nomSci;
    private int _nb_jours_interArrossage;
    private float _lum;
    private String _dateNextArrosage;

    public Plante() {

    }

    // création du constructeur de la classe plante
    public Plante(String nom, String nomSci, int nb_jours_interArrossage, float lum) {
        this._nom = nom;
        this._nomSci = nomSci;
        this._nb_jours_interArrossage = nb_jours_interArrossage;
        this._lum = lum;

        // Récuparration de la date du jour qui sera considéré comme le premier arrosage
        Calendar calendar = Calendar.getInstance();
        // Ajout des jours jusqu'au prochain arrosage
        calendar.add(Calendar.DAY_OF_MONTH, _nb_jours_interArrossage);
        // conversion de la date en chaine de caractere
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY");
        String date = dateFormat.format(calendar.getTime());
        this._dateNextArrosage = date;
    }

    public void set_nom(String _nom) {
        this._nom = _nom;
    }

    public void set_nomSci(String _nomSci) {
        this._nomSci = _nomSci;
    }

    public void set_nb_jours_interArrossage(int _jourBtArrosage) {
        this._nb_jours_interArrossage = _jourBtArrosage;
    }

    public void set_lum(float _lum) {
        this._lum = _lum;
    }

    public void set_dateNextArrosage(String _dateNextArrosage) {
        this._dateNextArrosage = _dateNextArrosage;
    }


    // Définition de fonction permettant de récupérer les attributs de la plant
    public String get_nom() {
        return _nom;
    }

    public String get_nomSci() {
        return _nomSci;
    }

    public int get_nb_jours_interArrossage() {
        return _nb_jours_interArrossage;
    }

    public float get_lum() {
        return _lum;
    }

    public String get_dateNextArrosage() {
        return _dateNextArrosage;
    }
}
