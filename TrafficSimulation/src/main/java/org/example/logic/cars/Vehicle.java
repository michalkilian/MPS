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

    public void accelerate(double acceleration, int carMaxSpeed) {
        if (this.speed + acceleration > carMaxSpeed) this.speed = carMaxSpeed;
        else if ( (int) (this.speed + acceleration) <= 0) this.speed = 1;
        else this.speed += acceleration;
    }
}
