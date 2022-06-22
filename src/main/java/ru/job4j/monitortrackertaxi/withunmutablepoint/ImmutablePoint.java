package ru.job4j.monitortrackertaxi.withunmutablepoint;

import net.jcip.annotations.Immutable;

@Immutable
public class ImmutablePoint {

    public final int x, y;

    public ImmutablePoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

}
