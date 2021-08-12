package ru.job4j.chess.firuges.black;

public interface Figure {
    Cell position();

    Cell[] way(Cell source, Cell dest);

    Figure copy(Cell dest);
}
