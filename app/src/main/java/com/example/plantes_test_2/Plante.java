package com.example.plantes_test_2;

import java.util.Calendar;
import java.util.Date;

public class Plante {

    // définition des
    private String _nom;
    private String _nomSci;
    private int _nb_jours_interArrossage;
    private float _lum;
    private Date _dateNextArrosage;

    public Plante() {

    }

    // création du constructeur de la classe plante
    public Plante(String nom, String nomSci, int nb_jours_interArrossage, float lum) {
        this._nom = nom;
        this._nomSci = nomSci;
        this._nb_jours_interArrossage = _nb_jours_interArrossage;
        this._lum = lum;

        // Récuparration de la date du jour qui sera considéré comme le premier arrosage
        Calendar calendar = Calendar.getInstance();
        // Ajouts des jours jusqu'au prochain arrosage
        calendar.add(Calendar.DATE, _nb_jours_interArrossage);
        this._dateNextArrosage = calendar.getTime();
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

    public void set_dateNextArrosage(Date _dateNextArrosage) {
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

    public Date get_dateNextArrosage() {
        return _dateNextArrosage;
    }
}
