package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.logic.Junction;
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

        Junction junction = new Junction(roadWidthCm, roadLengthCm, squareSizeCm);
        junction.placeVehicle(new Vehicle( 0,  roadLengthCm/squareSizeCm + 2, Direction.WEST, Type.CAR, 2));
        junction.placeVehicle(new Vehicle(5,roadLengthCm/squareSizeCm + 2, Direction.WEST, Type.CAR, 1));

        for(int i=0; i<20; i++)
        {
            try {
                PrintWriter p = new PrintWriter( "gridVisualizations/vis-"+junction.getSimulationTime()+".txt", StandardCharsets.UTF_8);
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
