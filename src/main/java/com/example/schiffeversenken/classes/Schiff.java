package com.example.schiffeversenken.classes;

import java.util.ArrayList;

public abstract class Schiff {
    private int felder;
    private String name;
    private static ArrayList<Schiff> schiffsListe = new ArrayList<>();

    protected Schiff(int felder){
        this.felder = felder;
        schiffsListe.add(this);
    }
}
