package ru.job4j.monitortrackertaxi.withmutablepoint;

public class MutablePoint {

    public int x, y;

    public MutablePoint(MutablePoint loc) {
        this.x = loc.x;
        this.y = loc.y;
    }

    public MutablePoint() {
        x = 0;
        y = 0;
    }

}
