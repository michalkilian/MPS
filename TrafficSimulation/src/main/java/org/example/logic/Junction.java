package org.example.logic;


import javafx.util.Pair;
import lombok.Getter;
import org.example.logic.Lights.Light;
import org.example.logic.Lights.TrafficLight;
import org.example.logic.cars.Direction;
import org.example.logic.cars.Vehicle;

import java.util.HashMap;
import java.util.Map;

@Getter
public class Junction {
    int roadWidth;
    int roadLength;
    int roadBorder;
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
        this.roadBorder = this.roadWidth / 4;

        grid = new Grid(roadWidthCm / squareSizeCm, roadLengthCm / squareSizeCm);
        lightsWest = new TrafficLight(Direction.WEST, 10, 1, 10, 1, Light.GREEN);
        lightsNorth = new TrafficLight(Direction.NORTH, 10, 1, 10, 1, Light.GREEN);
        lightsSouth = new TrafficLight(Direction.SOUTH, 10, 1, 10, 1, Light.GREEN);
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

        int carSizeSquares = (settings.carSizeCm / settings.squareSizeCm);
        for (Vehicle v : vehicles.values()) {
            int x = v.getX();
            int y = v.getY();
            int i, distanceToStop;
            double acceleration;

            // WEST
            if (v.getFrom() == Direction.WEST && !v.isTurned() || v.getTo() == Direction.EAST && v.isTurned()) {
                for (i = x + 1; i < 2 * roadLength + roadWidth; i++)
                    if (grid.get(i, y) != 0) break;

                // Nearest car further than lights
                if (i >= roadLength && x <= (roadLength - 1) - (carSizeSquares - 1) && lightsWest.getState() != Light.GREEN) {
                    distanceToStop = Math.abs(x - (roadLength - 1)) - (carSizeSquares - 1);
                    x += v.breaking(distanceToStop, false, settings);
                }
                // Nearest car before lights
                else if (i < roadLength) {
                    distanceToStop = Math.abs(x - i) - (carSizeSquares - 1) - settings.minimalInterCarDistance;
                    x += v.breaking(distanceToStop, false, settings);
                }
                // No obstacles
                else {
                    if (v.getTo() == Direction.SOUTH) {
                        distanceToStop = Math.abs(x - (roadLength + roadBorder));
                        x += v.breaking(distanceToStop, true, settings);
                    } else if (v.getTo() == Direction.NORTH) {
                        distanceToStop = Math.abs(x - (roadLength + roadWidth - roadBorder));
                        x += v.breaking(distanceToStop, true, settings);
                    } else if (v.getTo() == Direction.EAST) {
                        v.accelerate(settings.carAcceleration, settings.carMaxSpeed);
                        x += v.getSpeed();
                    }
                }
            }
            // NORTH
            else if (v.getFrom() == Direction.NORTH && !v.isTurned() || v.getTo() == Direction.SOUTH && v.isTurned()) {
                for (i = y + 1; i < 2 * roadLength + roadWidth; i++)
                    if (grid.get(x, i) != 0) break;

                // Nearest car further than lights
                if (i >= roadLength && y <= (roadLength - 1) - (carSizeSquares - 1)) {
                    if (lightsWest.getState() != Light.GREEN) {
                        distanceToStop = Math.abs(y - (roadLength - 1)) - (carSizeSquares - 1);
                        if (distanceToStop > 0) {
                            acceleration = -Math.pow(v.getSpeed(), 2) / (2 * distanceToStop);
                            v.accelerate(acceleration, settings.carMaxSpeed);
                            y += v.getSpeed();
                        } else v.setSpeed(0);
                    } else {
                        // if (direction)
                        if (v.getTo() == Direction.WEST) {
                            distanceToStop = Math.abs(y - (roadLength + roadBorder));
                            if (distanceToStop > 0) {
                                acceleration = -Math.pow(v.getSpeed(), 2) / (2 * distanceToStop);
                                v.accelerate(acceleration, settings.carMaxSpeed);
                                y += v.getSpeed();
                            } else {
                                v.setSpeed(0);
                                v.setTurned(true);
                            }
                        } else if (v.getTo() == Direction.SOUTH) {
                            v.accelerate(settings.carAcceleration, settings.carMaxSpeed);
                            y += v.getSpeed();
                        }
                    }
                    // A car in front
                } else if (i < roadLength) {
                    distanceToStop = Math.abs(y - i) - (carSizeSquares - 1) - settings.minimalInterCarDistance;
                    if (distanceToStop > 0) {
                        acceleration = -Math.pow(v.getSpeed(), 2) / (2 * distanceToStop);
                        v.accelerate(acceleration, settings.carMaxSpeed);
                        y += v.getSpeed();
                    } else v.setSpeed(0);
                    // Car not found
                } else {
                    v.accelerate(settings.carAcceleration, settings.carMaxSpeed);
                    y += v.getSpeed();
                }
            }
            // EAST
            else if (v.getFrom() == Direction.EAST && !v.isTurned() || v.getTo() == Direction.WEST && v.isTurned()) {
                System.out.println("Todo");
            }
            // SOUTH
            else if (v.getFrom() == Direction.SOUTH && !v.isTurned() || v.getTo() == Direction.NORTH && v.isTurned()) {
                for (i = y - 1; i >= 0; i--)
                    if (grid.get(x, i) != 0) break;

                // Nearest car further than lights
                if (i >= roadLength + roadWidth && y >= (roadLength + roadWidth + 1) - (carSizeSquares - 1)) {
                    if (lightsWest.getState() != Light.GREEN) {
                        distanceToStop = Math.abs(y - (roadLength + roadWidth - 1)) - (carSizeSquares - 1);
                        if (distanceToStop > 0) {
                            acceleration = -Math.pow(v.getSpeed(), 2) / (2 * distanceToStop);
                            v.accelerate(acceleration, settings.carMaxSpeed);
                            y -= v.getSpeed();
                        } else v.setSpeed(0);
                    } else {
                        // if (direction)
                        if (v.getTo() == Direction.EAST) {
                            distanceToStop = Math.abs(y - (roadLength + roadWidth - roadBorder));
                            if (distanceToStop > 0) {
                                acceleration = -Math.pow(v.getSpeed(), 2) / (2 * distanceToStop);
                                v.accelerate(acceleration, settings.carMaxSpeed);
                                y -= v.getSpeed();
                            } else {
                                v.setSpeed(0);
                                v.setTurned(true);
                            }
                        } else if (v.getTo() == Direction.NORTH) {
                            v.accelerate(settings.carAcceleration, settings.carMaxSpeed);
                            y -= v.getSpeed();
                        }
                    }
                    // A car in front
                } else if (i > roadLength + roadWidth) {
                    distanceToStop = Math.abs(x - i) - (carSizeSquares - 1) - settings.minimalInterCarDistance;
                    if (distanceToStop > 0) {
                        acceleration = -Math.pow(v.getSpeed(), 2) / (2 * distanceToStop);
                        v.accelerate(acceleration, settings.carMaxSpeed);
                        x += v.getSpeed();
                    } else v.setSpeed(0);
                    // Car not found
                } else {
                    v.accelerate(settings.carAcceleration, settings.carMaxSpeed);
                    y -= v.getSpeed();
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
