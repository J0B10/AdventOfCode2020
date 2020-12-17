package de.ungefroren.adventofcode.y2020.day17;

public class Bounds {
    private int maxX;
    private int minX;
    private int maxY;
    private int minY;
    private int maxZ;
    private int minZ;
    private int maxW;
    private int minW;

    public void update(Coordinates c) {
        if (minX > c.x) minX = c.x;
        if (minY > c.y) minY = c.y;
        if (minZ > c.z) minZ = c.z;
        if (minW > c.w) minW = c.w;
        if (maxX < c.x) maxX = c.x;
        if (maxY < c.y) maxY = c.y;
        if (maxZ < c.z) maxZ = c.z;
        if (maxW < c.w) maxW = c.w;
    }

    public void update(Iterable<Coordinates> all) {
        all.forEach(this::update);
    }

    public int getMaxX() {
        return maxX;
    }

    public int getMinX() {
        return minX;
    }

    public int getMaxY() {
        return maxY;
    }

    public int getMinY() {
        return minY;
    }

    public int getMaxZ() {
        return maxZ;
    }

    public int getMinZ() {
        return minZ;
    }

    public int getMaxW() {
        return maxW;
    }

    public int getMinW() {
        return minW;
    }

    @Override
    public String toString() {
        return "Bounds{" + minX + "<=X<=" + maxX + "|" + minY + "<=Y<=" + maxY + "|" + minZ + "<=Z<=" + maxZ + "|" + minW + "<=W<=" + maxW + "}";
    }
}
