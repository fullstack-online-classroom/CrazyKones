package com.codeforall.online.carcrash.cars;

import com.codeforall.online.carcrash.grid.Position;

public abstract class Car {

    /** The position of the car on the grid */
    private Position pos;

    public Position getPos() {
        return pos;
    }

    public boolean isCrashed() {
        return false;
    }


}
