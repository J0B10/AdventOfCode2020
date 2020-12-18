package de.ungefroren.adventofcode.y2020.day18.tokens;

public class Number implements Token {

    private final double value;

    public Number(double value) {
        this.value = value;
    }

    @Override
    public double resolve() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
