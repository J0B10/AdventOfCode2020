package de.ungefroren.adventofcode.y2020.day11;

public enum Sightline {

    NORTH(0, -1),
    NORTHEAST(1, -1),
    EAST(1,0),
    SOUTHEAST(1, 1),
    SOUTH(0,1),
    SOUTHWEST(-1, 1),
    WEST(-1, 0),
    NORTHWEST(-1, -1);

    private final int incrementX;
    private final int incrementY;

    Sightline(int incrementX, int incrementY) {
        this.incrementX = incrementX;
        this.incrementY = incrementY;
    }

    public boolean inSight(int x, int y, Sitable[][] seats) {
        while (true) {
            x += incrementX;
            y += incrementY;
            if (x < 0 || x >= seats[0].length) return false;
            if (y < 0 || y >= seats.length) return false;
            if (seats[y][x].isOccupied()) return true;
            else if (seats[y][x] instanceof Seat) return false;
        }
    }
}
