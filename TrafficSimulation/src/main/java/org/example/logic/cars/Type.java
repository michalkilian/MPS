package org.example.logic.cars;

import lombok.Getter;

@Getter
public enum Type {
    NONE((byte)0), CAR((byte)1), TRUCK((byte) 2);
    private final byte value;

    Type(byte value) {
        this.value = value;
    }

}
