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

    private static ArrayList<String> spieldfeld = new ArrayList<>();

    protected Schiff(int felder, String name){
        if (spieldfeld.size() == 0){
            for (int i = 65; i <= 75; i++){
                for (int j = 1; j <= 10; j++){
                    spieldfeld.add(Character.toString(i)+j);
                }
            }
        }
        this.name = name;
        this.felder = felder;
        schiffsListe.add(this);
        ++counter;

    }


    public HashMap<String, ArrayList<String>> setzeSchiff(){

        HashMap<String, ArrayList<String>> hm = new HashMap<>();
        ArrayList<String> al = new ArrayList<>();
        Random r = new Random();
        int startv;
        int starth;
/*        do {
            startv = r.nextInt(65, 76);
            starth = r.nextInt(1, this.getFelder()+1);

        }while (besetzteFelder.contains(""+starth+startv));*/

        boolean run = true;
        do {
            startv = r.nextInt(65, 76);
            starth = r.nextInt(1, this.getFelder()+1);
            if (startv%2==0){
                for (int i = 0; i< this.getFelder(); i++){
                    String feld = Character.toString(startv) + (starth+i);
                    if (spieldfeld.contains(feld)){
                        continue;
                    }
                    run = false;
                }
            } else{
                for (int i = 0; i< this.getFelder(); i++){
                    String feld = Character.toString(startv + i) + starth;
                    if (spieldfeld.contains(feld)){
                        continue;
                    }
                    run = false;
                }
            }
        } while (run);

        if (startv%2 == 0){
            System.out.println("Start Horizontal: " +starth);
            for (int i = 0; i < this.getFelder(); i++){
                String feld = Character.toString(startv) + (starth+i);
                al.add(feld);
                if (besetzteFelder.contains(feld)){
                    System.out.println("Logikfehler");
                }
                spieldfeld.remove(feld);
            }
        } else {
            System.out.println("Start Vertikal: " +startv);
            for (int i = 0; i < this.getFelder(); i++){
                String Feld = Character.toString(startv +i) + starth;
                al.add(Feld);
                if (besetzteFelder.contains(Feld)){
                    System.out.println("Logikfehler");
                }
                spieldfeld.remove(felder);
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
