package org.example.logic;

public class Settings {

    public static boolean initialized = false;

    //public String[] times = new String[]{"Noc", "Poranek", "Popołudnie"};
    private int time;
    //public static int[] throughput = {10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10};

    // Vehicles' settings
    public static int minimalInterCarDistance = 2;
    //Cars'
    public static int carMaxVelocity = 3;
    public static int carMaxSpeed = 2;
    public static int carAcceleration = 1;
    public static int carMaxUpperVelocity = 2;
    public static int carSize = 30;//450; // carSize/squareSize = 2k + 1, k in N


    public Settings() {
//        time = 2;
//        // Vehicles'
//        minimalInterCarDistance = 30;
//        // Cars'
//        carMaxVelocity = 3;
//        carMaxUpperVelocity = 5;
//        carSize = 450;
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
