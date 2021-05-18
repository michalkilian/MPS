package org.example.logic.cars;

import lombok.Data;

@Data
public class Vehicle {
    int x;
    int y;
    Direction from;
    Direction to;
    Type type;
}
