package de.ungefroren.adventofcode.y2020.day11;

public class Seat implements Sitable {

    private final boolean occupied;

    public Seat(boolean occupied) {
        this.occupied = occupied;
    }

    public Seat() {
        this(false);
    }

    @Override
    public boolean isOccupied() {
        return occupied;
    }

    @Override
    public String toString() {
        return occupied ? "#" : "L";
    }
}
