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
    Settings settings;
    int simulationTime;

    public Grid grid;
    Map<Pair<Integer, Integer>, Vehicle> vehicles = new HashMap<>();
    TrafficLight lightsWest;
    TrafficLight lightsNorth;
    TrafficLight lightsSouth;
    TrafficLight lightsEast;


    public Junction(Settings settings) {
        this.settings = settings;
        int squareSizeCm = settings.squareSizeCm;
        int roadWidthCm = settings.roadWidthCm;
        int roadLengthCm = settings.roadLengthCm;
        if (roadWidthCm % squareSizeCm != 0 || roadLengthCm % squareSizeCm != 0)
            throw new IllegalArgumentException("Wrong size of roadWidthCm or roadLengthCm");

        this.roadWidth = roadWidthCm / squareSizeCm;
        this.roadLength = roadLengthCm / squareSizeCm;

        grid = new Grid(roadWidthCm / squareSizeCm, roadLengthCm / squareSizeCm);
        lightsWest = new TrafficLight(Direction.WEST, 10, 1, 10, 1, Light.RED);
        lightsNorth = new TrafficLight(Direction.NORTH, 10, 1, 10, 1, Light.GREEN);
        lightsSouth = new TrafficLight(Direction.SOUTH, 10, 1, 10, 1, Light.RED);
        lightsEast = new TrafficLight(Direction.EAST, 10, 1, 10, 1, Light.GREEN);
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

//        for (Vehicle v : vehicles.values()) {
//            int x = v.getX();
//            int y = v.getY();
//            if (v.getFrom() == Direction.WEST) {
//                if (v.getX() > roadLength - 2 && v.getX() < roadLength && lightsWest.getState() != Light.GREEN) ;
//                else x += v.getSpeed();
//            } else if (v.getFrom() == Direction.NORTH) {
//                if (v.getY() > roadLength - 2 && v.getY() < roadLength && lightsNorth.getState() != Light.GREEN) ;
//                else y += v.getSpeed();
//            } else if (v.getFrom() == Direction.EAST) {
//                if (v.getX() < roadLength + roadWidth + 2 && v.getX() > roadLength + roadWidth && lightsEast.getState() != Light.GREEN)
//                    ;
//                else x -= v.getSpeed();
//            } else if (v.getFrom() == Direction.SOUTH) {
//                if (v.getY() < roadLength + roadWidth + 2 && v.getY() > roadLength + roadWidth && lightsSouth.getState() != Light.GREEN)
//                    ;
//                else y -= v.getSpeed();
//            }
//            moveVehicle(v, x, y);
//        }

        // All cars goes forward
        int carSizeSquares = (settings.carSizeCm / settings.squareSizeCm);
        for (Vehicle v : vehicles.values()) {
            int x = v.getX();
            int y = v.getY();
            int i, distanceToStop;
            double acceleration;

            // Direction and position on the grid (in the future)
            if (v.getFrom() == Direction.WEST) {
                for (i = x + 1; i < 2 * roadLength + roadWidth; i++)
                    if (grid.get(i, y) != 0) break;

                if (i >= roadLength && x <= (roadLength - 1) - (carSizeSquares - 1)) {
                    if (lightsWest.getState() != Light.GREEN) {
                        distanceToStop = Math.abs(x - (roadLength - 1)) - (carSizeSquares - 1);
                        if (distanceToStop > 0) {
                            acceleration = -Math.pow(v.getSpeed(), 2) / (2 * distanceToStop);
                            v.accelerate(acceleration, settings.carMaxSpeed);
                            x += v.getSpeed();
                        } else v.setSpeed(0);
                    } else {
                        v.accelerate(settings.carAcceleration, settings.carMaxSpeed);
                        x += v.getSpeed();
                    }
                } else if (i < roadLength) {
                    distanceToStop = Math.abs(x - i) - (carSizeSquares - 1) - settings.minimalInterCarDistance;
                    if (distanceToStop > 0) {
                        acceleration = -Math.pow(v.getSpeed(), 2) / (2 * distanceToStop);
                        v.accelerate(acceleration, settings.carMaxSpeed);
                        x += v.getSpeed();
                    } else v.setSpeed(0);
                } else {
                    v.accelerate(settings.carAcceleration, settings.carMaxSpeed);
                    x += v.getSpeed();
                }
            }
            moveVehicle(v, x, y);
        }

        lightsEast.tick();
        lightsNorth.tick();
        lightsWest.tick();
        lightsSouth.tick();
    }

    public String lightsState() {
        return String.valueOf(lightsWest) + lightsNorth + lightsEast + lightsSouth;
    }
}
