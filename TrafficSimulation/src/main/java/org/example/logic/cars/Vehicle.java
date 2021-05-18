package org.example.logic.cars;

import lombok.Data;

@Data
public class Vehicle {
    int x;
    int y;
    Direction from;
    Direction to;
    Type type;
    int distanceNearestPrev;
    int distanceNearest;
    int speed;

    public Vehicle(int x, int y, Direction from, Type type, int speed) {
        this.x = x;
        this.y = y;
        this.from = from;
        this.type = type;
        this.speed = speed;
    }
}
