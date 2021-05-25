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
            int i, distanceToStop, distanceToCarStop;
            double acceleration;

            // WEST
            if (v.getFrom() == Direction.WEST && !v.isTurned() || v.getTo() == Direction.EAST && v.isTurned()) {
                for (i = x + 1; i < 2 * roadLength + roadWidth; i++)
                    if (grid.get(i, y) != 0) break;

                distanceToCarStop = Math.abs(x - i) - (carSizeSquares - 1) - settings.minimalInterCarDistance;

                // Nearest car further than lights
                if (i >= roadLength && x <= (roadLength - 1) - (carSizeSquares - 1) && lightsWest.getState() != Light.GREEN) {
                    distanceToStop = Math.abs(x - (roadLength - 1)) - (carSizeSquares - 1);
                    x += v.breaking(distanceToStop, false, settings);
                }
                // Nearest car before lights
                else if (i < roadLength) {
                    distanceToStop = distanceToCarStop;
                    x += v.breaking(distanceToStop, false, settings);
                }
                // Car passed lights
                else {
                    if (v.getTo() == Direction.SOUTH) {
                        distanceToStop = Math.min(Math.abs(x - (roadLength + roadBorder)), distanceToCarStop);
                        x += v.breaking(distanceToStop, distanceToStop != distanceToCarStop, settings);
                    } else if (v.getTo() == Direction.NORTH) {
                        distanceToStop = Math.min(Math.abs(x - (roadLength + roadWidth - roadBorder - 1)), distanceToCarStop);
                        x += v.breaking(distanceToStop, distanceToStop != distanceToCarStop, settings);
                    } else if (v.getTo() == Direction.EAST) {
                        if (i == 2 * roadLength + roadWidth) {
                            v.accelerate(settings.carAcceleration, settings.carMaxSpeed);
                            x += v.getSpeed();
                        } else {
                            distanceToStop = distanceToCarStop;
                            x += v.breaking(distanceToStop, false, settings);
                        }
                    }
                }
            }
            // NORTH
            else if (v.getFrom() == Direction.NORTH && !v.isTurned() || v.getTo() == Direction.SOUTH && v.isTurned()) {
                for (i = y + 1; i < 2 * roadLength + roadWidth; i++)
                    if (grid.get(x, i) != 0) break;

                distanceToCarStop = Math.abs(y - i) - (carSizeSquares - 1) - settings.minimalInterCarDistance;

                // Nearest car further than lights
                if (i >= roadLength && y <= (roadLength + 1) - (carSizeSquares - 1) && lightsNorth.getState() != Light.GREEN) {
                    distanceToStop = Math.abs(y - (roadLength - 1)) - (carSizeSquares - 1);
                    y += v.breaking(distanceToStop, false, settings);
                }
                // Nearest car before lights
                else if (i < roadLength) {
                    distanceToStop = distanceToCarStop;
                    y += v.breaking(distanceToStop, false, settings);
                }
                // Car passed lights
                else {
                    if (v.getTo() == Direction.WEST) {
                        distanceToStop = Math.min(Math.abs(y - (roadLength + roadBorder)), distanceToCarStop);
                        y += v.breaking(distanceToStop, distanceToStop != distanceToCarStop, settings);
                    } else if (v.getTo() == Direction.EAST) {
                        distanceToStop = Math.min(Math.abs(y - (roadLength + roadWidth - roadBorder - 1)), distanceToCarStop);
                        y += v.breaking(distanceToStop, distanceToStop != distanceToCarStop, settings);
                    } else if (v.getTo() == Direction.SOUTH) {
                        if (i == 2 * roadLength + roadWidth) {
                            v.accelerate(settings.carAcceleration, settings.carMaxSpeed);
                            y += v.getSpeed();
                        } else {
                            distanceToStop = distanceToCarStop;
                            y += v.breaking(distanceToStop, false, settings);
                        }
                    }
                }
            }
            // EAST
            else if (v.getFrom() == Direction.EAST && !v.isTurned() || v.getTo() == Direction.WEST && v.isTurned()) {
                for (i = x - 1; i >= 0; i--)
                    if (grid.get(i, y) != 0) break;

                distanceToCarStop = Math.abs(x - i) - (carSizeSquares - 1) - settings.minimalInterCarDistance;

                // Nearest car further than lights
                if (i <= roadLength + roadWidth && x >= (roadLength + roadWidth + 1) - (carSizeSquares - 1) && lightsEast.getState() != Light.GREEN) {
                    distanceToStop = Math.abs(x - (roadLength + roadWidth + 1)) - (carSizeSquares - 1);
                    x -= v.breaking(distanceToStop, false, settings);
                }
                // Nearest car before lights
                else if (i > roadLength + roadWidth) {
                    distanceToStop = distanceToCarStop;
                    x -= v.breaking(distanceToStop, false, settings);
                }
                // Car passed lights
                else {
                    if (v.getTo() == Direction.NORTH) {
                        distanceToStop = Math.min(Math.abs(x - (roadLength + roadWidth - roadBorder - 1)), distanceToCarStop);
                        x -= v.breaking(distanceToStop, distanceToStop != distanceToCarStop, settings);
                    } else if (v.getTo() == Direction.SOUTH) {
                        distanceToStop = Math.min(Math.abs(x - (roadLength + roadBorder)), distanceToCarStop);
                        x -= v.breaking(distanceToStop, distanceToStop != distanceToCarStop, settings);
                    } else if (v.getTo() == Direction.WEST) {
                        if (i == -1) {
                            v.accelerate(settings.carAcceleration, settings.carMaxSpeed);
                            x -= v.getSpeed();
                        } else {
                            distanceToStop = distanceToCarStop;
                            x -= v.breaking(distanceToStop, false, settings);
                        }
                    }
                }
            }
            // SOUTH
            else if (v.getFrom() == Direction.SOUTH && !v.isTurned() || v.getTo() == Direction.NORTH && v.isTurned()) {
                for (i = y - 1; i >= 0; i--)
                    if (grid.get(x, i) != 0) break;

                distanceToCarStop = Math.abs(y - i) - (carSizeSquares - 1) - settings.minimalInterCarDistance;

                // Nearest car further than lights
                if (i <= roadLength + roadWidth && y >= (roadLength + roadWidth + 1) - (carSizeSquares - 1) && lightsSouth.getState() != Light.GREEN) {
                    distanceToStop = Math.abs(y - (roadLength + roadWidth + 1)) - (carSizeSquares - 1);
                    y -= v.breaking(distanceToStop, false, settings);
                }
                // Nearest car before lights
                else if (i > roadLength + roadWidth) {
                    distanceToStop = distanceToCarStop;
                    y -= v.breaking(distanceToStop, false, settings);
                }
                // Car passed lights
                else {
                    if (v.getTo() == Direction.EAST) {
                        distanceToStop = Math.min(Math.abs(y - (roadLength + roadWidth - roadBorder - 1)), distanceToCarStop);
                        y -= v.breaking(distanceToStop, distanceToStop != distanceToCarStop, settings);
                    } else if (v.getTo() == Direction.WEST) {
                        distanceToStop = Math.min(Math.abs(y - (roadLength + roadBorder)), distanceToCarStop);
                        y -= v.breaking(distanceToStop, distanceToStop != distanceToCarStop, settings);
                    } else if (v.getTo() == Direction.NORTH) {
                        if (i == -1) {
                            v.accelerate(settings.carAcceleration, settings.carMaxSpeed);
                            y -= v.getSpeed();
                        } else {
                            distanceToStop = distanceToCarStop;
                            y -= v.breaking(distanceToStop, false, settings);
                        }
                    }
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
