package logic;

import logic.cars.Type;
import logic.cars.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class Junction {
    float roadWidthCm;
    float roadLengthCm;
    float squareSizeCm;

    public Grid grid;
    List<Vehicle> vehicles = new ArrayList<>();

    public Junction(int roadWidthCm, int roadLengthCm, int squareSizeCm) {
        if (roadWidthCm % squareSizeCm != 0 || roadLengthCm % squareSizeCm != 0) throw new IllegalArgumentException();

        this.roadWidthCm = roadWidthCm;
        this.roadLengthCm = roadLengthCm;
        this.squareSizeCm = squareSizeCm;

        grid = new Grid(roadWidthCm / squareSizeCm, roadLengthCm / squareSizeCm);
    }

    public void moveVehicle(Vehicle vehicle, int x, int y) {
        grid.set(vehicle.getX(), vehicle.getY(), Type.NONE.getValue());
        vehicle.setX(x);
        vehicle.setY(y);
        grid.set(vehicle.getX(), vehicle.getY(), vehicle.getType().getValue());
    }


}
