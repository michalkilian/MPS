package org.example.logic;

public class Settings {

    public static boolean initialized = false;

    //public String[] times = new String[]{"Noc", "Poranek", "Popołudnie"};
    private int time;
    //public static int[] throughput = {10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10};

    // Vehicles' settings
    public static int minimalInterCarDistance;
    //Cars'
    public static int carMaxVelocity;
    public static int carMaxUpperVelocity;
    public static int carSize;


    public Settings() {
        time = 2;
        // Vehicles'
        minimalInterCarDistance = 30;
        // Cars'
        carMaxVelocity = 3;
        carMaxUpperVelocity = 5;
        carSize = 450;
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
