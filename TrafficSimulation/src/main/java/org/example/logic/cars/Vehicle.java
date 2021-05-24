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
    boolean turned;

    public Vehicle(int x, int y, Direction from, Direction to, Type type, int speed) {
        this.x = x;
        this.y = y;
        this.from = from;
        this.to = to;
        this.type = type;
        this.speed = speed;
    }

    public void accelerate(double acceleration, int carMaxSpeed) {
        if (this.speed + acceleration > carMaxSpeed) this.speed = carMaxSpeed;
        else if ((int) (this.speed + acceleration) <= 0) this.speed = 1;
        else this.speed += acceleration;
    }

    public int breaking(int distanceToStop, boolean turn, Settings settings) {
        if (distanceToStop > 0) {
            double acceleration = -Math.pow(this.getSpeed(), 2) / (2 * distanceToStop);
            this.accelerate(acceleration, settings.carMaxSpeed);
            return this.speed;
        }
        else {
            if (turn) this.turned = true;
            this.speed = 0;
            return 0;
        }
    }
}
