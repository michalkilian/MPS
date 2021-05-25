package org.example.view;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.example.logic.cars.Vehicle;

public class Cell extends Rectangle {
    private final int width = 25;
    private final int height = 25;
    private final int x;
    private final int y;
    private Color color;
    private Vehicle vehicle;

    public void setColor(Color color){
        this.color = color;
        this.setFill(color);
    }

    public Color getColor() {
        return color;
    }

    public Cell(Color c, int x, int y){
        this.color = c;
        this.x = x;
        this.y = y;

        setFill(c);
        setHeight(this.height);
        setWidth(this.width);
        relocate(x*this.width, y*this.height);
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
