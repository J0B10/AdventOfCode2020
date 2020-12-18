package de.ungefroren.adventofcode.y2020.day18;

import java.util.Arrays;

public enum Operator {
    PLUS('+', 1, 0) {
        @Override
        public double calculate(double val1, double val2) {
            return val1 + val2;
        }
    },

    MULTIPLY('*', 1, 1) {
        @Override
        public double calculate(double val1, double val2) {
            return val1 * val2;
        }
    };

    private final char symbol;
    private final int priority;
    private final double neutralElement;

    Operator(char symbol, int priority, double neutralElement) {
        this.symbol = symbol;
        this.priority = priority;
        this.neutralElement = neutralElement;
    }

    public char getSymbol() {
        return symbol;
    }

    public int getPriority() {
        return priority;
    }

    public double getNeutralElement() {
        return neutralElement;
    }

    @Override
    public String toString() {
        return String.valueOf(getSymbol());
    }

    public abstract double calculate(double val1, double val2);

    public static Operator valueOf(char c) throws IllegalArgumentException {
        return Arrays.stream(values()).filter(o -> o.symbol == c).findAny()
                .orElseThrow(() -> new IllegalArgumentException(c + " is not a valid operator"));
    }

    public static boolean isOperator(char c) {
        return Arrays.stream(values()).anyMatch(o -> o.symbol == c);
    }
}
