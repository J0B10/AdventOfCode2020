package de.ungefroren.adventofcode.y2020.day20;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tile {

    public static final int DEFAULT_SIZE = 10;
    public final int size;
    private final int id;
    private final boolean[][] values;

    public Tile(int id, boolean[][] values) {
        if (values.length != values[0].length)
            throw new IllegalArgumentException("all tiles must be squared");
        this.size = values.length;
        this.id = id;
        this.values = values;
    }

    public static Tile parse(String s) {
        boolean[][] values = new boolean[DEFAULT_SIZE][DEFAULT_SIZE];
        String[] lines = s.split("\\n");
        if (lines.length - 1 != DEFAULT_SIZE) throw new IllegalArgumentException("all tiles must be 10x10");
        Matcher m = Pattern.compile("Tile (?<id>\\d+):").matcher(lines[0]);
        if (!m.find()) throw new IllegalArgumentException("tile id not found");
        int id = Integer.parseInt(m.group("id"));
        for (int y = 0; y < DEFAULT_SIZE; y++) {
            if (lines[y + 1].length() != DEFAULT_SIZE) throw new IllegalArgumentException("all tiles must be 10x10");
            for (int x = 0; x < DEFAULT_SIZE; x++) {
                values[x][y] = lines[y + 1].charAt(x) == '#';
            }
        }
        return new Tile(id, values);
    }

    public int getId() {
        return id;
    }

    public Tile rotate90() {
        boolean[][] cp = new boolean[size][size];
        for (int i = 0; i < size / 2; i++) {
            for (int j = i; j < size - i - 1; j++) {
                cp[i][size - j - 1] = values[j][i];
                cp[size - j - 1][size - i - 1] = values[i][size - j - 1];
                cp[size - i - 1][j] = values[size - j - 1][size - i - 1];
                cp[j][i] = values[size - i - 1][j];

            }
        }
        return new Tile(id, cp);
    }

    public Tile flipHor() {
        boolean[][] cp = new boolean[size][size];
        for (int y = 0; y < size; y++) {
            for (int i = 0; i < size / 2; i++) {
                cp[i][y] = values[size - i - 1][y];
                cp[size - i - 1][y] = values[i][y];
            }
        }
        return new Tile(id, cp);
    }

    private boolean matchNorth(Tile t) {
        for (int x = 0; x < size; x++) {
            if (this.values[x][0] != t.values[x][size - 1]) return false;
        }
        return true;
    }

    private boolean matchEast(Tile t) {
        for (int y = 0; y < size; y++) {
            if (this.values[size - 1][y] != t.values[0][y]) return false;
        }
        return true;
    }

    private boolean matchSouth(Tile t) {
        for (int x = 0; x < size; x++) {
            if (this.values[x][size - 1] != t.values[x][0]) return false;
        }
        return true;
    }

    private boolean matchWest(Tile t) {
        for (int y = 0; y < size; y++) {
            if (this.values[0][y] != t.values[size - 1][y]) return false;
        }
        return true;
    }

    public boolean matches(Direction direction, Tile t) {
        switch (direction) {
            case NORTH: return matchNorth(t);
            case EAST: return matchEast(t);
            case SOUT: return matchSouth(t);
            case WEST: return matchWest(t);
            default: return false;
        }
    }
    public Direction matches(Tile t) {
        for (Direction d : Direction.values()) {
            if (matches(d, t)) return d;
        }
        return null;
    }

    public boolean[][] getValues() {
        return values;
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder("Tile ").append(id).append(":\n");
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                b.append(values[x][y] ? '#' : '.');
            }
            b.append('\n');
        }
        return b.toString();
    }
}
