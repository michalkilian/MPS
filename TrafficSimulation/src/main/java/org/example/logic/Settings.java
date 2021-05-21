package org.example.logic;

public class Settings {

    public static boolean initialized = false;

    //public String[] times = new String[]{"Noc", "Poranek", "Popołudnie"};
    private int time;
    //public static int[] throughput = {10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10};

    // Junction's
    public int squareSizeCm;
    public int roadWidthCm;
    public int roadLengthCm;
    // Vehicles'
    public int minimalInterCarDistance;
    //Cars'
    // Marcin's
    public int carMaxSpeed;
    public int carAcceleration;
    public int carSizeCm;
    // Michal's
    public static int carMaxVelocity = 3;
    public static int carMaxUpperVelocity = 2;

    public Settings() {}
    public Settings(int squareSizeCm, int roadWidthCm, int roadLengthCm, int minimalInterCarDistance, int carMaxSpeed, int carAcceleration, int carSizeCm) {
        this.squareSizeCm = squareSizeCm;
        this.roadWidthCm = roadWidthCm;
        this.roadLengthCm = roadLengthCm;
        this.minimalInterCarDistance = minimalInterCarDistance;
        this.carMaxSpeed = carMaxSpeed;
        this.carAcceleration = carAcceleration;
        this.carSizeCm = carSizeCm;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getCarMaxVelocity() {
        return carMaxVelocity;
    }

    public void setCarMaxVelocity(int carMaxVelocity) {
        Settings.carMaxVelocity = carMaxVelocity;
    }

    public int getCarMaxUpperVelocity() {
        return carMaxUpperVelocity;
    }

    public void setCarMaxUpperVelocity(int carMaxUpperVelocity) {
        Settings.carMaxUpperVelocity = carMaxUpperVelocity;
    }
}
