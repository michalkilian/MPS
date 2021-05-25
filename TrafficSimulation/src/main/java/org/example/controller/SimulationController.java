package org.example.controller;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.example.logic.Junction;
import org.example.logic.Settings;
import org.example.logic.Simulation;
import org.example.logic.cars.Direction;
import org.example.logic.cars.Type;
import org.example.logic.cars.Vehicle;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SimulationController extends BaseController implements Initializable {
    public Button backButton;

    public Button magicStartButton;

    public Simulation simulation;

    @FXML
    private List<Label> labelList;





    AnimationTimer h = new AnimationTimer() {
        int i = 0;

        @Override
        public void handle(long now) {
//            if (i % 60 == 0) {
//                Highway.resetNumbersOfCarOnSegments();
//                for (Road r : simulation.getHighway().roads) {
//                    r.generateNextFrame();
//                }
//                Lane.iterCounter++;
//                updateLabelsText();
//            }
//            i += 1;
        }
    };

    @Override
    protected void initSettings(Settings settings) {
        this.settings = settings;
    }


    void initSimulation() {
        this.simulation = new Simulation(settings);
    }

    void initSimulation(Simulation simulation) {
        this.simulation = simulation;
    }

    @Override
    public void goBackToMenu(ActionEvent event) throws IOException {
        h.stop();
        super.goBackToMenu(event);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Junction junction = new Junction(settings);
        junction.placeVehicle(new Vehicle(0, settings.roadLengthCm / settings.squareSizeCm + 2, Direction.WEST, Type.CAR, 2));
        junction.placeVehicle(new Vehicle(5, settings.roadLengthCm / settings.squareSizeCm + 2, Direction.WEST, Type.CAR, 1));
//        updateLabelsText();

    }

//    private void updateLabelsText() {
//        for (int i = 0; i < labelList.size() / 2; ++i) {
//            labelList.get(i).setText(String.valueOf(Highway.carsOnSegment.get(i)));
//            labelList.get(i + labelList.size() / 2).setText(String.valueOf(Road.carsPerMinute[i]));
//        }
    }




