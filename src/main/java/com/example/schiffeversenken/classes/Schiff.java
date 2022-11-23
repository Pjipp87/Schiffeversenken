package com.example.schiffeversenken.classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

public abstract class Schiff {
    protected static int counter;
    private int felder;
    private String name;
    private static ArrayList<Schiff> schiffsListe = new ArrayList<>();
    private static ArrayList<String> besetzteFelder = new ArrayList<>();

    protected Schiff(int felder, String name){
        this.name = name;
        this.felder = felder;
        schiffsListe.add(this);
        ++counter;
    }


    public HashMap<String, ArrayList<String>> setzeSchiff(){
        String ausrichtung = null;
        HashMap<String, ArrayList<String>> hm = new HashMap<>();
        ArrayList<String> al = new ArrayList<>();
        Random r = new Random();

        if (r.nextInt(0,3) ==1){
            ausrichtung = "v";
        } else {
            ausrichtung = "h";
        }
        if (ausrichtung.equals("h")){
            int startv = r.nextInt(65, 76);
            int starth = r.nextInt(1, this.getFelder()+1);
            System.out.println("Start Horizontal: " +starth);
            for (int i = 0; i < this.getFelder(); i++){
                String Feld = Character.toString(startv + i) + starth;
                al.add(Feld);
                if (besetzteFelder.contains(Feld)){
                    System.out.println("Logikfehler");
                }
                besetzteFelder.add(Feld);
            }
        } if (ausrichtung.equals("v")){
            int startv = r.nextInt(65, 76);
            int starth = r.nextInt(1, this.getFelder()+1);
            System.out.println("Start Vertikal: " +startv);
            for (int i = 0; i < this.getFelder(); i++){
                String Feld = Character.toString(startv + i) + starth;
                al.add(Feld);
                if (besetzteFelder.contains(Feld)){
                    System.out.println("Logikfehler");
                }
                besetzteFelder.add(Feld);
            }
        }
        hm.put(this.getName(), al);
        return hm;
    }

    public String getName() {
        return name;
    }

    public int getFelder() {
        return felder;
    }
}
