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
import org.example.logic.cars.Direction;
import org.example.logic.cars.Type;
import org.example.logic.cars.Vehicle;
import org.example.view.JunctionGrid;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class SimulationController extends BaseController implements Initializable {
    public Button backButton;

    public Junction junction;
    public ScrollPane scrollPane;
    public Pane lightsEast;
    public Pane lightsWest;
    public Pane lightsNorth;
    public Pane lightsSouth;
    private GridPane gridPane;
    private JunctionGrid junctionGrid;
    public boolean [] freeLanes;

    @FXML
    private List<Label> labelList;

    AnimationTimer h = new AnimationTimer() {
        int i = 1;

        @Override
        public void handle(long now) {
            if (i % 60 == 0) {
                  junction.tick();
                  scrollPane.setContent(junctionGrid.updateColors(junction));
                  setLights();
                  generateCar();
           }
            i += 1;
        }
    };

    @Override
    protected void initSettings(Settings settings) {
        this.settings = settings;
    }


    void initSimulation() {
        junction = new Junction(settings);
        this.junctionGrid = new JunctionGrid(junction.getRoadWidth(), junction.getRoadLength());
        this.gridPane = (GridPane) junctionGrid.getFxGrid();
        this.scrollPane.setContent(this.gridPane);


        junction.placeVehicle(new Vehicle(0, settings.roadLengthCm / settings.squareSizeCm + 4, Direction.WEST, Direction.EAST, Type.CAR, 2));
        junction.placeVehicle(new Vehicle(2, settings.roadLengthCm / settings.squareSizeCm + 4, Direction.WEST, Direction.NORTH, Type.CAR, 1));
        junction.placeVehicle(new Vehicle(4, settings.roadLengthCm / settings.squareSizeCm + 4, Direction.WEST, Direction.SOUTH, Type.CAR, 1));

        junction.placeVehicle(new Vehicle(settings.roadLengthCm/settings.squareSizeCm + 1, 0, Direction.NORTH, Direction.SOUTH, Type.CAR, 2));
        junction.placeVehicle(new Vehicle(settings.roadLengthCm/settings.squareSizeCm + 1, 3, Direction.NORTH, Direction.EAST, Type.CAR, 1));

        junction.placeVehicle(new Vehicle((2 * settings.roadLengthCm + settings.roadWidthCm)/settings.squareSizeCm - 1, settings.roadLengthCm / settings.squareSizeCm + 1, Direction.EAST, Direction.WEST, Type.CAR, 2));

        junction.placeVehicle(new Vehicle(settings.roadLengthCm/settings.squareSizeCm + 4, (2 * settings.roadLengthCm + settings.roadWidthCm)/settings.squareSizeCm - 1, Direction.SOUTH, Direction.NORTH, Type.CAR, 2));

        this.scrollPane.setContent(junctionGrid.updateColors(junction));
        setLights();

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

    public void generateCar(){
        Random rand = new Random();
        double car_generated_rand = rand.nextDouble();
        System.out.println(settings.getTraffic());
        if((settings.getTraffic() == 0 && car_generated_rand <= 0.33) || (settings.getTraffic() == 1 && car_generated_rand <= 0.22) || (settings.getTraffic() == 2 && car_generated_rand <= 0.11)){
            freeLanes = new boolean[] {false, false, false, false};
            boolean generateCar = false;
            //1st spots
            // WEST junction.placeVehicle(new Vehicle(0, settings.roadLengthCm / settings.squareSizeCm + 4, Direction.WEST, Direction.EAST, Type.CAR, 2));
            // NOTYH junction.placeVehicle(new Vehicle(roadLengthCm/squareSizeCm + 1, 0, Direction.NORTH, Direction.SOUTH, Type.CAR, 2));
            // EAST junction.placeVehicle(new Vehicle((2 * roadLengthCm + roadWidthCm)/squareSizeCm - 1, roadLengthCm / squareSizeCm + 1, Direction.EAST, Direction.WEST, Type.CAR, 2));
            // SOUTH junction.placeVehicle(new Vehicle(roadLengthCm/squareSizeCm + 4, (2 * roadLengthCm + roadWidthCm)/squareSizeCm - 1, Direction.SOUTH, Direction.NORTH, Type.CAR, 2));

            //WEST CHECK
            freeLanes[0] = junction.getGrid().get(0, settings.roadLengthCm / settings.squareSizeCm + 4) == 0;
            //NORTH CHECK
            freeLanes[1] = junction.getGrid().get(settings.roadLengthCm/settings.squareSizeCm + 1, 0) == 0;
            //EAST CHECK
            freeLanes[2] = junction.getGrid().get((2 * settings.roadLengthCm + settings.roadWidthCm)/settings.squareSizeCm - 1, settings.roadLengthCm / settings.squareSizeCm + 1) == 0;
            //SOUTH CHECK
            freeLanes[3] = junction.getGrid().get(settings.roadLengthCm/settings.squareSizeCm + 4, (2 * settings.roadLengthCm +settings.roadWidthCm)/settings.squareSizeCm - 1) == 0;

            for (boolean freeLane : freeLanes) {

                // accessing each element of array
                if (freeLane) {
                    generateCar = true;
                    break;
                }
            }
            if (!generateCar) {
                return;
            }
            int direction = rand.nextInt(4);
            while(!freeLanes[direction]){
                direction = rand.nextInt(4);
            }
            double targetDirectionProb = rand.nextDouble();
            Direction targetDirection;
            switch (direction){
                case 0:
                    if (targetDirectionProb < settings.probLeft){
                        targetDirection = Direction.NORTH;
                    }
                    else if (targetDirectionProb < (settings.probLeft + settings.probStraight)){
                        targetDirection = Direction.EAST;
                    }
                    else{
                        targetDirection = Direction.SOUTH;
                    }
                    junction.placeVehicle(new Vehicle(0, settings.roadLengthCm / settings.squareSizeCm + 4, Direction.WEST, targetDirection, Type.CAR, 1));
                    break;
                case 1:
                    if (targetDirectionProb < settings.probLeft){
                        targetDirection = Direction.EAST;
                    }
                    else if (targetDirectionProb < (settings.probLeft + settings.probStraight)){
                        targetDirection = Direction.SOUTH;
                    }
                    else{
                        targetDirection = Direction.WEST;
                    }
                    junction.placeVehicle(new Vehicle(settings.roadLengthCm/settings.squareSizeCm + 1, 0, Direction.NORTH, targetDirection, Type.CAR, 1));
                    break;
                case 2:
                    if (targetDirectionProb < settings.probLeft){
                        targetDirection = Direction.SOUTH;
                    }
                    else if (targetDirectionProb < (settings.probLeft + settings.probStraight)){
                        targetDirection = Direction.WEST;
                    }
                    else{
                        targetDirection = Direction.NORTH;
                    }
                    junction.placeVehicle(new Vehicle((2 * settings.roadLengthCm + settings.roadWidthCm)/settings.squareSizeCm - 1, settings.roadLengthCm / settings.squareSizeCm + 1, Direction.EAST, targetDirection, Type.CAR, 1));
                    break;
                case 3:
                    if (targetDirectionProb < settings.probLeft){
                        targetDirection = Direction.WEST;
                    }
                    else if (targetDirectionProb < (settings.probLeft + settings.probStraight)){
                        targetDirection = Direction.NORTH;
                    }
                    else{
                        targetDirection = Direction.EAST;
                    }
                    junction.placeVehicle(new Vehicle(settings.roadLengthCm/settings.squareSizeCm + 4, (2 * settings.roadLengthCm +settings.roadWidthCm)/settings.squareSizeCm - 1, Direction.SOUTH, targetDirection, Type.CAR, 1));
                    break;
                default:
                    break;
            }


        }
    }

//    private void updateLabelsText() {
//        for (int i = 0; i < labelList.size() / 2; ++i) {
//            labelList.get(i).setText(String.valueOf(Highway.carsOnSegment.get(i)));
//            labelList.get(i + labelList.size() / 2).setText(String.valueOf(Road.carsPerMinute[i]));
//        }
    }




