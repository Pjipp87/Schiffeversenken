package com.example.schiffeversenken;

import com.example.schiffeversenken.classes.*;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;


public class SchiffeversenkenController {
   @FXML
    private GridPane gridPane = new GridPane();

   public static ArrayList<String> schiffeAufSpielfeld = new ArrayList<>();
    public static HashMap<String, ArrayList<String>> schiffeAufSpielfeldMap = new HashMap<>();

   @FXML
   private Label status;

   @FXML
    private ListView<String> liste;


    @FXML
    protected void initialize() {


        for (int i = 0; i< gridPane.getColumnCount(); i++){
            for(int j =0; j < gridPane.getRowCount(); j++){
               Label playButton;
                if (i==0 && j ==0){
                    playButton = new Label();
                    playButton.setVisible(false);

                } else if (i ==0){
                    playButton = new Label("  "+Character.toString(j+64));
                } else if (j == 0){
                    playButton = new Label(" "+(i));
                } else {
                    playButton = new Label(""+Character.toString(j+64)+i);
                    playButton.setStyle("-fx-text-fill: lightblue");
                    playButton.setPrefWidth(gridPane.getPrefWidth());
                    playButton.setPrefHeight(gridPane.getPrefHeight());
                }
                playButton.setFont(Font.font("Bold", 13));
                gridPane.add(playButton, i,j);
            }
        }



        schiffeAufSpielfeldMap.putAll(new Schlachtschiff().setzeSchiff());
        schiffeAufSpielfeldMap.putAll(new Kreuzer().setzeSchiff());
        schiffeAufSpielfeldMap.putAll(new Kreuzer().setzeSchiff());
        schiffeAufSpielfeldMap.putAll(new Zerstörer().setzeSchiff());
        schiffeAufSpielfeldMap.putAll(new Zerstörer().setzeSchiff());
        schiffeAufSpielfeldMap.putAll(new Zerstörer().setzeSchiff());
        schiffeAufSpielfeldMap.putAll(new Uboot().setzeSchiff());
        schiffeAufSpielfeldMap.putAll(new Uboot().setzeSchiff());
        schiffeAufSpielfeldMap.putAll(new Uboot().setzeSchiff());
        schiffeAufSpielfeldMap.putAll(new Uboot().setzeSchiff());

        System.out.println();
        schiffeAufSpielfeldMap.forEach((k,v)->{
            System.out.println(k+": "+v);
        });
        System.out.println();
        System.out.println(Schiff.spieldfeld.size());
        System.out.println("\nUsereingaben:");


        for (Node element: gridPane.getChildren()) {
            element.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    int column = GridPane.getColumnIndex(element);
                    int row = GridPane.getRowIndex(element);
                    String shoot = Character.toString(row + 64) + String.valueOf(column);
                    System.out.println(shoot);
                    final String[] schiffsName = {"Null"};

                    schiffeAufSpielfeldMap.forEach((k,v)->{
                        if (v.contains(shoot)){
                            schiffsName[0] = k;
                            v.remove(shoot);
                            for (Node b: gridPane.getChildren()){
                                if (element.equals(b)){
                                    //gridPane.setStyle("-fx-background-color: red");
                                    b.setStyle("-fx-background-color: red; -fx-text-fill: red");
                                }

                            }
                            System.out.println(v);
                            if (v.size() > 0){
                                liste.getItems().add("Treffer: "+k);


                            } else{
                                liste.getItems().add(k+" versenkt");


                            }
                            liste.scrollTo(liste.getItems().size());
                        }

                    });
                    if (schiffeAufSpielfeldMap.containsKey(schiffsName[0])){
                        if (schiffeAufSpielfeldMap.get(schiffsName[0]).size() == 0){
                            schiffeAufSpielfeldMap.remove(schiffsName[0]);
                            if (schiffeAufSpielfeldMap.isEmpty()){
                                liste.getItems().add("Spiel beendet!");
                            }
                        }
                    }

                    ;

                }
            });
        }

    }





}