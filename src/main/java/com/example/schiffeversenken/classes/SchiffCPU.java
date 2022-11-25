package com.example.schiffeversenken.classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public abstract class SchiffCPU {
    protected static int counter;
    private int felder;
    private String name;
    private static ArrayList<SchiffCPU> schiffsListeCPU = new ArrayList<>();
 

    public static ArrayList<String> spieldfeldCPU = new ArrayList<>();

    protected SchiffCPU(int felder, String name){
        if (spieldfeldCPU.size() == 0){
            for (int i = 65; i <= 74; i++){
                for (int j = 1; j <= 10; j++){
                    spieldfeldCPU.add(Character.toString(i)+j);
                }
            }
            System.out.println(spieldfeldCPU.size());
        }
        this.name = name;
        this.felder = felder;
        schiffsListeCPU.add(this);
        ++counter;

    }


    public HashMap<String, ArrayList<String>> setzeSchiff(){

        HashMap<String, ArrayList<String>> hm = new HashMap<>();
        ArrayList<String> al = new ArrayList<>();
        Random r = new Random();
        int startv;
        int starth;

        boolean ausrichtung = r.nextBoolean();
        boolean run = true;

        do {
            if (ausrichtung){
                startv = r.nextInt(65, 76);
                starth = r.nextInt(1, this.getFelder()+1);
                ArrayList<String> tempList = new ArrayList<>();
                for (int i = 0; i< this.getFelder(); i++){
                    String feld = Character.toString(startv) + (starth+i);
                    tempList.add(feld);
                }
                if (spieldfeldCPU.containsAll(tempList)){
                    hm.put(this.getName(), tempList);
                    spieldfeldCPU.removeAll(tempList);
                    System.out.println(tempList + " Entfernt!");

                    run = false;

                } else {

                    tempList.clear();
                }
            } else {
                ArrayList<String> tempList = new ArrayList<>();
                startv = r.nextInt(65, 76-this.getFelder());
                starth = r.nextInt(1, 11);
                for (int i = 0; i< this.getFelder(); i++){
                    String feld = Character.toString(startv+i) + (starth);
                    tempList.add(feld);
                }
                if (spieldfeldCPU.containsAll(tempList)){
                    hm.put(this.getName(), tempList);
                    System.out.println(tempList);
                    spieldfeldCPU.removeAll(tempList);
                    System.out.println(tempList + " Entfernt!");
                    run = false;

                } else {

                    tempList.clear();
                }
            }
        } while (run);

       /* if (ausrichtung%2 == 0){
            System.out.println(this.getName()+" Start Horizontal: " +starth);
            for (int i = 0; i < this.getFelder(); i++){
                String feld = Character.toString(startv) + (starth+i);
                if (!spieldfeld.contains(feld)){
                    al.add(feld);
                } else {
                    al.add(feld);
                    System.err.println("Logikfehler horizontal: "+feld);
                }


            }
        } else {
            System.out.println(this.getName()+" Start Vertikal: " +startv);
            for (int i = 0; i < this.getFelder(); i++){
                String feld = Character.toString(startv +i) + starth;

                if (!spieldfeld.contains(feld)){
                    al.add(feld);
                } else {
                    al.add(feld);
                    System.err.println("Logikfehler Vertikal: "+feld);
                }


            }
        }*/
        //hm.put(this.getName(), al);
        return hm;
    }

    public String getName() {
        return name;
    }

    public int getFelder() {
        return felder;
    }
}
