package org.example.logic;

public class Settings {

    public static boolean initialized = false;

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
    public String[] traffics = new String[]{"High", "Moderate", "Low"};
    private int traffic;
    public double probLeft = 0;
    public double probStraight = 0;
    public double probRight = 1;

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

    public int getTraffic() {
        return traffic;
    }

    public void setTraffic(int traffic) {
        this.traffic = traffic;
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
