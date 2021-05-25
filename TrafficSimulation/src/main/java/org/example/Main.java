package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.logic.Junction;
import org.example.logic.Settings;
import org.example.logic.cars.Direction;
import org.example.logic.cars.Type;
import org.example.logic.cars.Vehicle;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        primaryStage.setTitle("MPS Projekt");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
//        launch(args);
        int squareSizeCm = 30;
        int roadWidthCm = 6*squareSizeCm;
        int roadLengthCm = 8*squareSizeCm;

        Settings settings = new Settings(squareSizeCm, roadWidthCm, roadLengthCm, 2, 3, 1, squareSizeCm);

        Junction junction = new Junction(settings);
        // WEST tests
//        junction.placeVehicle(new Vehicle(0, roadLengthCm / squareSizeCm + 4, Direction.WEST, Direction.EAST, Type.CAR, 2));
//        junction.placeVehicle(new Vehicle(3, roadLengthCm / squareSizeCm + 4, Direction.WEST, Direction.NORTH, Type.CAR, 1));
//        junction.placeVehicle(new Vehicle(6, roadLengthCm / squareSizeCm + 4, Direction.WEST, Direction.SOUTH, Type.CAR, 1));
        // NORTH tests
        junction.placeVehicle(new Vehicle(roadLengthCm/squareSizeCm + 1, 0, Direction.NORTH, Direction.SOUTH, Type.CAR, 2));
        junction.placeVehicle(new Vehicle(roadLengthCm/squareSizeCm + 1, 3, Direction.NORTH, Direction.EAST, Type.CAR, 1));
        junction.placeVehicle(new Vehicle(roadLengthCm/squareSizeCm + 1, 6, Direction.NORTH, Direction.WEST, Type.CAR, 1));
        // EAST tests
//        junction.placeVehicle(new Vehicle((2 * roadLengthCm + roadWidthCm)/squareSizeCm - 1, roadLengthCm / squareSizeCm + 1, Direction.EAST, Direction.WEST, Type.CAR, 2));
//        junction.placeVehicle(new Vehicle((2 * roadLengthCm + roadWidthCm)/squareSizeCm - 4, roadLengthCm / squareSizeCm + 1, Direction.EAST, Direction.SOUTH, Type.CAR, 1));
//        junction.placeVehicle(new Vehicle((2 * roadLengthCm + roadWidthCm)/squareSizeCm - 7, roadLengthCm / squareSizeCm + 1, Direction.EAST, Direction.NORTH, Type.CAR, 1));



        for (int i = 0; i < 20; i++) {
            try {
                PrintWriter p = new PrintWriter("gridVisualizations/vis-" + junction.getSimulationTime() + ".txt", StandardCharsets.UTF_8);
                p.println(junction.getSimulationTime());
                p.println("---");
                p.print(junction.lightsState());
                p.println("---");
                p.println(junction.grid.toString());
                p.close();
                junction.tick();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
