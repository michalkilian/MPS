package org.example.controller;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.example.logic.Junction;
import org.example.logic.Lights.Light;
import org.example.logic.Settings;
import org.example.logic.Simulation;
import org.example.logic.cars.Direction;
import org.example.logic.cars.Type;
import org.example.logic.cars.Vehicle;
import org.example.view.JunctionGrid;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class SimulationController extends BaseController implements Initializable {
    public Button backButton;

    public Button magicStartButton;
    public Junction junction;
    public Simulation simulation;
    public ScrollPane scrollPane;
    public Pane lightsEast;
    public Pane lightsWest;
    public Pane lightsNorth;
    public Pane lightsSouth;
    private GridPane gridPane;
    private JunctionGrid junctionGrid;

    @FXML
    private List<Label> labelList;



    private void updateGrid(){


    }

    AnimationTimer h = new AnimationTimer() {
        int i = 1;

        @Override
        public void handle(long now) {
            if (i % 60 == 0) {
                  junction.tick();
                  scrollPane.setContent(junctionGrid.updateColors(junction));
                  setLights();
//                Highway.resetNumbersOfCarOnSegments();
//                for (Road r : simulation.getHighway().roads) {
//                    r.generateNextFrame();
//                }
//                Lane.iterCounter++;
//                updateLabelsText();
           }
            i += 1;
        }
    };
//report - introduction - literature and references, description of the program (structure, maybe diagrams), example of executions + presentations of case studies + conclusions
    @Override
    protected void initSettings(Settings settings) {
        this.settings = settings;
    }


    void initSimulation() {
        junction = new Junction(settings);
        this.simulation = new Simulation(settings);
        this.junctionGrid = new JunctionGrid(junction.getRoadWidth(), junction.getRoadLength());
        this.gridPane = (GridPane) junctionGrid.getFxGrid();
        this.scrollPane.setContent(this.gridPane);
        junction.placeVehicle(new Vehicle(0, settings.roadLengthCm / settings.squareSizeCm + 4, Direction.WEST, Direction.EAST, Type.CAR, 2));
        junction.placeVehicle(new Vehicle(3, settings.roadLengthCm / settings.squareSizeCm + 4, Direction.WEST, Direction.NORTH, Type.CAR, 1));
        this.scrollPane.setContent(junctionGrid.updateColors(junction));
        setLights();

    }

    void initSimulation(Simulation simulation) {
        this.simulation = simulation;
    }
    void setLights(){
        Light lightEast = junction.getLightsEast().getState();
        Light lightWest = junction.getLightsWest().getState();
        Light lightNorth = junction.getLightsNorth().getState();
        Light lightSouth = junction.getLightsSouth().getState();
        if (lightEast == Light.GREEN){
            this.lightsEast.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        }
        else if (lightEast == Light.RED){
            this.lightsEast.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
        }
        else{
            this.lightsEast.setBackground(new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
        }
        if (lightWest == Light.GREEN){
            this.lightsWest.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        }
        else if (lightWest == Light.RED){
            this.lightsWest.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
        }
        else{
            this.lightsWest.setBackground(new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
        }
        if (lightNorth == Light.GREEN){
            this.lightsNorth.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        }
        else if (lightNorth == Light.RED){
            this.lightsNorth.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
        }
        else{
            this.lightsNorth.setBackground(new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
        }
        if (lightSouth == Light.GREEN){
            this.lightsSouth.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        }
        else if (lightSouth == Light.RED){
            this.lightsSouth.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
        }
        else{
            this.lightsSouth.setBackground(new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
        }

    }
    @Override
    public void goBackToMenu(ActionEvent event) throws IOException {
        h.stop();
        super.goBackToMenu(event);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

//        updateLabelsText();

    }

//    private void updateLabelsText() {
//        for (int i = 0; i < labelList.size() / 2; ++i) {
//            labelList.get(i).setText(String.valueOf(Highway.carsOnSegment.get(i)));
//            labelList.get(i + labelList.size() / 2).setText(String.valueOf(Road.carsPerMinute[i]));
//        }
    }




