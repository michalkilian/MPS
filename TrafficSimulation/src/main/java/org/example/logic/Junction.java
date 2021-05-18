package org.example.logic;


import javafx.util.Pair;
import lombok.Getter;
import org.example.logic.cars.Direction;
import org.example.logic.cars.Vehicle;

import java.util.HashMap;
import java.util.Map;

@Getter
public class Junction {
    int roadWidth;
    int roadLength;
    int squareSizeCm;
    int simulationTime;

    public Grid grid;
    Map<Pair<Integer, Integer>, Vehicle> vehicles = new HashMap<>();
    TrafficLight lightsWest;
    TrafficLight lightsNorth;
    TrafficLight lightsSouth;
    TrafficLight lightsEast;


    public Junction(int roadWidthCm, int roadLengthCm, int squareSizeCm) {
        if (roadWidthCm % squareSizeCm != 0 || roadLengthCm % squareSizeCm != 0)
            throw new IllegalArgumentException("Wrong size of roadWidthCm or roadLengthCm");

        this.roadWidth = roadWidthCm / squareSizeCm;
        this.roadLength = roadLengthCm / squareSizeCm;
        this.squareSizeCm = squareSizeCm;

        grid = new Grid(roadWidthCm / squareSizeCm, roadLengthCm / squareSizeCm);
        lightsWest = new TrafficLight(Direction.WEST, 1, 1, 5, 1, Light.RED);
        lightsNorth = new TrafficLight(Direction.NORTH, 1, 1, 5, 1, Light.GREEN);
        lightsSouth = new TrafficLight(Direction.SOUTH, 1, 1, 5, 1, Light.RED);
        lightsEast = new TrafficLight(Direction.EAST, 1, 1, 5, 1, Light.GREEN);
    }

    public void moveVehicle(Vehicle v, int x, int y) {
        grid.set(v.getX(), v.getY(), 0);
        try {
            grid.set(x, y, v.getType().getValue());
            v.setX(x);
            v.setY(y);
        } catch (IndexOutOfBoundsException e) {
            vehicles.remove(new Pair<>(v.getX(), v.getY()));
        }
    }

    public void placeVehicle(Vehicle v) {
        vehicles.put(new Pair<>(v.getX(), v.getY()), v);
        grid.set(v.getX(), v.getY(), v.getType().getValue());
    }

    public void tick() {
        simulationTime++;

        for (Vehicle v : vehicles.values()) {
            int x = v.getX();
            int y = v.getY();
            if (v.getFrom() == Direction.WEST) {
                if (v.getX() > roadLength - 2 && v.getX() < roadLength && lightsWest.getState() != Light.GREEN) ;
                else x += v.getSpeed();
            } else if (v.getFrom() == Direction.NORTH) {
                if (v.getY() > roadLength - 2 && v.getY() < roadLength && lightsNorth.getState() != Light.GREEN) ;
                else y += v.getSpeed();
            } else if (v.getFrom() == Direction.EAST) {
                if (v.getX() < roadLength + roadWidth + 2 && v.getX() > roadLength + roadWidth && lightsEast.getState() != Light.GREEN)
                    ;
                else x -= v.getSpeed();
            } else if (v.getFrom() == Direction.SOUTH) {
                if (v.getY() < roadLength + roadWidth + 2 && v.getY() > roadLength + roadWidth && lightsSouth.getState() != Light.GREEN)
                    ;
                else y -= v.getSpeed();
            }
            moveVehicle(v, x, y);
        }

        lightsEast.tick();
        lightsNorth.tick();
        lightsWest.tick();
        lightsSouth.tick();

//        // Legacy code - will be used probably later
//        int carSizeSquares = (Settings.carSize / squareSizeCm);
//        int searchLength = Settings.minimalInterCarDistance / squareSizeCm + Settings.carSize / squareSizeCm;
//        int searchWidth = 4 * carSizeSquares;
//        int i, j;
//        for (Vehicle v : vehicles.values()) {
//            if (v.getFrom() == Direction.WEST) {
//                int startX = v.getX() + carSizeSquares / 2;
//                int startY = v.getY() - 2 * carSizeSquares;
//                for (i = startX; i <= startX + searchLength; i++) {
//                    for (j = startY; j <= startY + searchWidth; j++)
//                        if (grid.get(i, j) != 0 && vehicles.get(new Pair<>(i, j)).getFrom() == v.getFrom()) break;
//                }
//                if (Math.abs(v.getX() - i) > searchLength)
//                    System.out.println("Car can move forward");
//            } else if (v.getFrom() == Direction.NORTH) {
//                int startX = v.getX() - 2 * carSizeSquares;
//                int startY = v.getY() + carSizeSquares / 2;
//                for (j = startY; j <= startY + searchLength; j++) {
//                    for (i = startX; i <= startX + searchWidth; i++)
//                        if (grid.get(i, j) != 0 && vehicles.get(new Pair<>(i, j)).getFrom() == v.getFrom()) break;
//                }
//                if (Math.abs(v.getY() - j) > searchLength)
//                    System.out.println("Car can move forward");
//            } else if (v.getFrom() == Direction.EAST) {
//                int startX = v.getX() - carSizeSquares / 2; // car's front
//                int startY = v.getY() - 2 * carSizeSquares;
//                for (i = startX; i >= startX - searchLength; i--) {
//                    for (j = startY; j <= startY + searchWidth; j++)
//                        if (grid.get(i, j) != 0 && vehicles.get(new Pair<>(i, j)).getFrom() == v.getFrom()) break;
//                }
//                if (Math.abs(v.getX() - i) > searchLength)
//                    System.out.println("Car can move forward");
//            } else if (v.getFrom() == Direction.SOUTH) {
//                int startX = v.getX() - 2 * carSizeSquares; // car's front
//                int startY = v.getY() - carSizeSquares / 2;
//                for (j = startY; j >= startY - searchLength; j--) {
//                    for (i = startX; i <= startX + searchWidth; i++)
//                        if (grid.get(i, j) != 0 && vehicles.get(new Pair<>(i, j)).getFrom() == v.getFrom()) break;
//                }
//                if (Math.abs(v.getY() - j) > searchLength)
//                    System.out.println("Car can move forward");
//            }
//        }

    }

    public String lightsState() {
        return String.valueOf(lightsWest) + lightsNorth + lightsEast + lightsSouth;
    }
}
