package com.example.schiffeversenken;

import com.example.schiffeversenken.classes.Schlachtschiff;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public class SchiffeversenkenController {
   @FXML
    private GridPane gridPane = new GridPane();


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
                    playButton = new Label("");
                } else if (i ==0){
                    playButton = new Label("  "+Character.toString(j+64));
                } else if (j == 0){
                    playButton = new Label("  "+(i));
                } else {
                    playButton = new Label(" "+Character.toString(j+64)+i);
                    playButton.setStyle("-fx-text-fill: lightblue");
                    //button.setVisible(false);
                }
                playButton.setFont(Font.font("Bold", 18));
                gridPane.add(playButton, i,j);
            }
        }


        ArrayList<String> ar = new ArrayList<>();
        ar.add("A1");
        ar.add("A2");
        ar.add("A3");
        ar.add(new Schlachtschiff().setzeSchiff());




        for (Node element: gridPane.getChildren()) {
            element.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    int row = GridPane.getColumnIndex(element);
                    int column = GridPane.getRowIndex(element);
                    String shoot = Character.toString(row + 64) + String.valueOf(column);
                    System.out.println(shoot);

                    if (ar.contains(shoot)){
                        if (ar.size() == 1){
                            liste.getItems().add("Schiff versenkt");

                        } else{
                            liste.getItems().add("Treffer");
                            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("/sound/ex.mp3");


                        }

                        ar.remove(shoot);

                    }


                    //System.out.println("Spalte: "+ (GridPane.getColumnIndex(element)));
                    //System.out.println("Reihe: "+(Character.toString(GridPane.getRowIndex(element)+64)));
                }
            });
        }

    }





}