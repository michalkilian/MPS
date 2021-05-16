package org.example.logic.cars;

import lombok.Getter;

@Getter
public enum Type {
    CAR(1), TRUCK( 2);
    private final int value;

    Type(int value) {
        this.value = value;
    }

}
