package de.ungefroren.adventofcode.y2020.day17;

import java.util.Objects;

public class Coordinates {

    public final int x;
    public final int y;
    public final int z;
    public final int w;

    public Coordinates(int x, int y, int z) {
        this(x, y, z, 0);
    }

    public Coordinates(int x, int y, int z, int w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public Coordinates plus(int x, int y, int z, int w) {
        return new Coordinates(this.x + x, this.y + y, this.z + z, this.w + w);
    }


    public Coordinates plus(int x, int y, int z) {
        return new Coordinates(this.x + x, this.y + y, this.z + z);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return x == that.x && y == that.y && z == that.z && w == that.w;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z, w);
    }

    @Override
    public String toString() {
        return "[ " + x + " ; " + y + " ; " + z + " ; " + w +" ]";
    }
}
