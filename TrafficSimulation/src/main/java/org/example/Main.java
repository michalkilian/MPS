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

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

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
        int roadWidthCm = 180;
        int roadLengthCm = 210;
        int squareSizeCm = 30;

        Junction junction = new Junction(roadWidthCm, roadLengthCm, squareSizeCm);
        junction.placeVehicle(new Vehicle( 2,  roadLengthCm/squareSizeCm + 3, Direction.WEST, Type.CAR, 2));

        for(int i=0; i<10; i++)
        {
            try {
                PrintWriter p = new PrintWriter( "vis-"+junction.getSimulationTime()+".txt","UTF-8");
                p.println(junction.getSimulationTime());
                p.println("---");
                p.println(junction.grid.toString());
                p.close();
                junction.tick();
            } catch (FileNotFoundException | UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }
}
