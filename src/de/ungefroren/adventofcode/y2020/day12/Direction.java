package de.ungefroren.adventofcode.y2020.day12;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum Direction {

    NORTH, EAST, SOUTH, WEST;

    private static final List<Direction> VALUES = Collections.unmodifiableList(Arrays.asList(Direction.values()));

    public static Direction valueOf(char c) {
        return Arrays.stream(Direction.values()).filter(dir -> dir.name().charAt(0) == c).findAny()
                .orElseThrow(() -> new IllegalArgumentException(c + " is not a valid direction"));
    }

    public Direction plus(int degrees) {
        if (degrees % 90 != 0) throw new IllegalArgumentException();
        while (degrees < 0) degrees += 360;
        return VALUES.get((VALUES.indexOf(this) + degrees / 90) % VALUES.size());
    }

    @Override
    public String toString() {
        return name().substring(0, 1);
    }
}
