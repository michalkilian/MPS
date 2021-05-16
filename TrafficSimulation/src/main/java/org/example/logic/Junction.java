package org.example.logic;


import org.example.logic.cars.Type;
import org.example.logic.cars.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class Junction {
    float roadWidthCm;
    float roadLengthCm;
    float squareSizeCm;

    public Grid grid;
    List<Vehicle> vehicles = new ArrayList<>();
    List<TrafficLight> lights = new ArrayList<>();

    public Junction(int roadWidthCm, int roadLengthCm, int squareSizeCm) {
        if (roadWidthCm % squareSizeCm != 0 || roadLengthCm % squareSizeCm != 0) throw new IllegalArgumentException();

        this.roadWidthCm = roadWidthCm;
        this.roadLengthCm = roadLengthCm;
        this.squareSizeCm = squareSizeCm;

        grid = new Grid(roadWidthCm / squareSizeCm, roadLengthCm / squareSizeCm);
        this.lights.add(new TrafficLight(
                roadLengthCm, 0,
                roadLengthCm + roadWidthCm / 2, roadLengthCm,
                10, 10, 10, 10, Light.GREEN));
        this.lights.add(new TrafficLight(
                roadLengthCm + roadWidthCm, roadLengthCm,
                2 * roadLengthCm + roadWidthCm, roadLengthCm + roadWidthCm / 2,
                10, 10, 10, 10, Light.RED));
        this.lights.add(new TrafficLight(
                roadLengthCm + roadWidthCm / 2, roadLengthCm + roadWidthCm,
                roadLengthCm + roadWidthCm, 2*roadLengthCm + roadWidthCm,
                10, 10, 10, 10, Light.GREEN));
        this.lights.add(new TrafficLight(
                0, roadLengthCm + roadWidthCm / 2,
                roadLengthCm, roadLengthCm + roadWidthCm,
                10, 10, 10, 10, Light.RED));
    }

    public void moveVehicle(Vehicle vehicle, int x, int y) {
        grid.set(vehicle.getX(), vehicle.getY(), Type.NONE.getValue());
        vehicle.setX(x);
        vehicle.setY(y);
        grid.set(vehicle.getX(), vehicle.getY(), vehicle.getType().getValue());
    }


}
