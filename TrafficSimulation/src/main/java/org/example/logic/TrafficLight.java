package org.example.logic;


public class TrafficLight {
    // Area of influence (x1,y1) (x2,y2)
    int x1;
    int y1;
    int x2;
    int y2;
    int timeSinceChange = 0;
    int greenTime;
    int yellowTime;
    int redTime;
    int redYellowTime;
    Light state;

    public TrafficLight(int x1, int y1, int x2, int y2, int greenTime, int yellowTime, int redTime, int redYellowTime, Light state) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
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
    }
}
