package org.example.logic.cars;

import lombok.Data;
import org.example.logic.Settings;

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

    public void accelerate(double acceleration) {
        if (this.speed + acceleration > Settings.carMaxSpeed) this.speed = Settings.carMaxSpeed;
        else if ( (int) (this.speed + acceleration) <= 0) this.speed = 1;
        else this.speed += acceleration;
    }
}
