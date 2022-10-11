package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
        launch(args);
        int squareSizeCm = 30;
//        int roadWidthCm = 6*squareSizeCm;
//        int roadLengthCm = 8*squareSizeCm;
//
//        Settings settings = new Settings(squareSizeCm, roadWidthCm, roadLengthCm, 2, 3, 1, squareSizeCm);
//
//        Junction junction = new Junction(settings);
        // WEST tests
//        junction.placeVehicle(new Vehicle(0, roadLengthCm / squareSizeCm + 4, Direction.WEST, Direction.EAST, Type.CAR, 2));
//        junction.placeVehicle(new Vehicle(2, roadLengthCm / squareSizeCm + 4, Direction.WEST, Direction.NORTH, Type.CAR, 1));
//        junction.placeVehicle(new Vehicle(4, roadLengthCm / squareSizeCm + 4, Direction.WEST, Direction.SOUTH, Type.CAR, 1));
        // NORTH tests
   //     junction.placeVehicle(new Vehicle(roadLengthCm/squareSizeCm + 1, 0, Direction.NORTH, Direction.SOUTH, Type.CAR, 2));
//        junction.placeVehicle(new Vehicle(roadLengthCm/squareSizeCm + 1, 3, Direction.NORTH, Direction.EAST, Type.CAR, 1));
//        junction.placeVehicle(new Vehicle(roadLengthCm/squareSizeCm + 1, 6, Direction.NORTH, Direction.WEST, Type.CAR, 1));
        // EAST tests
//        junction.placeVehicle(new Vehicle((2 * roadLengthCm + roadWidthCm)/squareSizeCm - 1, roadLengthCm / squareSizeCm + 1, Direction.EAST, Direction.WEST, Type.CAR, 2));
//        junction.placeVehicle(new Vehicle((2 * roadLengthCm + roadWidthCm)/squareSizeCm - 4, roadLengthCm / squareSizeCm + 1, Direction.EAST, Direction.SOUTH, Type.CAR, 1));
//        junction.placeVehicle(new Vehicle((2 * roadLengthCm + roadWidthCm)/squareSizeCm - 7, roadLengthCm / squareSizeCm + 1, Direction.EAST, Direction.NORTH, Type.CAR, 1));
        // SOUTH tests
//        junction.placeVehicle(new Vehicle(roadLengthCm/squareSizeCm + 4, (2 * roadLengthCm + roadWidthCm)/squareSizeCm - 1, Direction.SOUTH, Direction.NORTH, Type.CAR, 2));
//        junction.placeVehicle(new Vehicle(roadLengthCm/squareSizeCm + 4, (2 * roadLengthCm + roadWidthCm)/squareSizeCm - 4, Direction.SOUTH, Direction.WEST, Type.CAR, 1));
//        junction.placeVehicle(new Vehicle(roadLengthCm/squareSizeCm + 4, (2 * roadLengthCm + roadWidthCm)/squareSizeCm - 7, Direction.SOUTH, Direction.EAST, Type.CAR, 1));


//        for (int i = 0; i < 150; i++) {
//                try {
//                    PrintWriter p = new PrintWriter("gridVisualizations/vis-" + junction.getSimulationTime() + ".txt", StandardCharsets.UTF_8);
//                    p.println(junction.getSimulationTime());
//                    p.println("---");
//                    p.print(junction.lightsState());
//                    p.println("---");
//                    p.println(junction.grid.toString());
//                    p.close();
//                    junction.tick();
//                    generateCar(settings, junction);
//                    System.out.println(i);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//        }
    }

//    public static void generateCar(Settings settings, Junction junction){
//        Random rand = new Random();
//        boolean [] freeLanes;
//        double car_generated_rand = rand.nextDouble();
//        if((settings.getTraffic() == 0 && car_generated_rand <= 0.33) || (settings.getTraffic() == 1 && car_generated_rand <= 0.22) || (settings.getTraffic() == 2 && car_generated_rand <= 0.11)){
//            freeLanes = new boolean[] {false, false, false, false};
//            boolean generateCar = false;
//            //1st spots
//            // WEST junction.placeVehicle(new Vehicle(0, settings.roadLengthCm / settings.squareSizeCm + 4, Direction.WEST, Direction.EAST, Type.CAR, 2));
//            // NOTYH junction.placeVehicle(new Vehicle(roadLengthCm/squareSizeCm + 1, 0, Direction.NORTH, Direction.SOUTH, Type.CAR, 2));
//            // EAST junction.placeVehicle(new Vehicle((2 * roadLengthCm + roadWidthCm)/squareSizeCm - 1, roadLengthCm / squareSizeCm + 1, Direction.EAST, Direction.WEST, Type.CAR, 2));
//            // SOUTH junction.placeVehicle(new Vehicle(roadLengthCm/squareSizeCm + 4, (2 * roadLengthCm + roadWidthCm)/squareSizeCm - 1, Direction.SOUTH, Direction.NORTH, Type.CAR, 2));
//
//            //WEST CHECK
//            freeLanes[0] = junction.getGrid().get(0, settings.roadLengthCm / settings.squareSizeCm + 4) == 0;
//            //NORTH CHECK
//            freeLanes[1] = junction.getGrid().get(settings.roadLengthCm/settings.squareSizeCm + 1, 0) == 0;
//            //EAST CHECK
//            freeLanes[2] = junction.getGrid().get((2 * settings.roadLengthCm + settings.roadWidthCm)/settings.squareSizeCm - 1, settings.roadLengthCm / settings.squareSizeCm + 1) == 0;
//            //SOUTH CHECK
//            freeLanes[3] = junction.getGrid().get(settings.roadLengthCm/settings.squareSizeCm + 4, (2 * settings.roadLengthCm +settings.roadWidthCm)/settings.squareSizeCm - 1) == 0;
//
//            for (boolean freeLane : freeLanes) {
//
//                // accessing each element of array
//                if (freeLane) {
//                    generateCar = true;
//                }
//            }
//            if (!generateCar) {
//                return;
//            }
//            int direction = 1;//rand.nextInt(4);
//            while(!freeLanes[direction]){
//                direction = rand.nextInt(4);
//            }
//            double targetDirectionProb = rand.nextDouble();
//            Direction targetDirection;
//            switch (direction){
//                case 0:
//                    if (targetDirectionProb < settings.probLeft){
//                        targetDirection = Direction.NORTH;
//                    }
//                    else if (targetDirectionProb < (settings.probLeft + settings.probStraight)){
//                        targetDirection = Direction.EAST;
//                    }
//                    else{
//                        targetDirection = Direction.SOUTH;
//                    }
//                    junction.placeVehicle(new Vehicle(0, settings.roadLengthCm / settings.squareSizeCm + 4, Direction.WEST, targetDirection, Type.CAR, 1));
//                    break;
//                case 1:
//                    if (targetDirectionProb < settings.probLeft){
//                        targetDirection = Direction.EAST;
//                    }
//                    else if (targetDirectionProb < (settings.probLeft + settings.probStraight)){
//                        targetDirection = Direction.SOUTH;
//                    }
//                    else{
//                        targetDirection = Direction.WEST;
//                    }
//                    junction.placeVehicle(new Vehicle(settings.roadLengthCm/settings.squareSizeCm + 1, 0, Direction.NORTH, targetDirection, Type.CAR, 1));
//                    break;
//                case 2:
//                    if (targetDirectionProb < settings.probLeft){
//                        targetDirection = Direction.SOUTH;
//                    }
//                    else if (targetDirectionProb < (settings.probLeft + settings.probStraight)){
//                        targetDirection = Direction.WEST;
//                    }
//                    else{
//                        targetDirection = Direction.NORTH;
//                    }
//                    junction.placeVehicle(new Vehicle((2 * settings.roadLengthCm + settings.roadWidthCm)/settings.squareSizeCm - 1, settings.roadLengthCm / settings.squareSizeCm + 1, Direction.EAST, targetDirection, Type.CAR, 1));
//                    break;
//                case 3:
//                    if (targetDirectionProb < settings.probLeft){
//                        targetDirection = Direction.WEST;
//                    }
//                    else if (targetDirectionProb < (settings.probLeft + settings.probStraight)){
//                        targetDirection = Direction.NORTH;
//                    }
//                    else{
//                        targetDirection = Direction.EAST;
//                    }
//                    junction.placeVehicle(new Vehicle(settings.roadLengthCm/settings.squareSizeCm + 4, (2 * settings.roadLengthCm +settings.roadWidthCm)/settings.squareSizeCm - 1, Direction.SOUTH, targetDirection, Type.CAR, 1));
//                    break;
//                default:
//                    break;
//            }
//
//
//        }
//    }
}
