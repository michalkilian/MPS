package org.example.logic;


import org.example.logic.cars.Direction;

public class TrafficLight {
    Direction forDirection;
    int timeSinceChange = 0;
    int greenTime;
    int yellowTime;
    int redTime;
    int redYellowTime;
    Light state;

    public TrafficLight(Direction forDirection, int greenTime, int yellowTime, int redTime, int redYellowTime, Light state) {
        this.forDirection = forDirection;
        this.greenTime = greenTime;
        this.yellowTime = yellowTime;
        this.redTime = redTime;
        this.redYellowTime = redYellowTime;
        this.state = state;
    }

    public void tick() {
        if (state == Light.GREEN && timeSinceChange == greenTime) {
            state = Light.YELLOW;
            timeSinceChange = 0;
        } else if (state == Light.YELLOW && timeSinceChange == yellowTime) {
            state = Light.RED;
            timeSinceChange = 0;
        } else if (state == Light.RED && timeSinceChange == redTime) {
            state = Light.RED_YELLOW;
            timeSinceChange = 0;
        } else if (state == Light.RED_YELLOW && timeSinceChange == redYellowTime) {
            state = Light.GREEN;
            timeSinceChange = 0;
        }
        timeSinceChange++;
    }
}
