package de.ungefroren.adventofcode.y2020.day12;

public class Pos {
    public final int x;
    public final int y;

    public Pos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Pos move(Direction direction, int distance) {
        switch (direction) {
            case NORTH:
                return new Pos(x, y + distance);
            case EAST:
                return new Pos(x + distance, y);
            case SOUTH:
                return new Pos(x, y - distance);
            case WEST:
                return new Pos(x - distance, y);
            default:
                return null;
        }
    }

    @Override
    public String toString() {
        return "[ " + x + "  " + y + " ]";
    }
}
