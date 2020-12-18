package de.ungefroren.adventofcode.y2020.day18;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;

public abstract class Operator {

    public static final Operator PLUS = new Operator('+', 1, 0) {
        @Override
        public double calculate(double val1, double val2) {
            return val1 + val2;
        }
    },

    MINUS = new Operator('-', 1, 0) {
        @Override
        public double calculate(double val1, double val2) {
            return val1 - val2;
        }
    },

    MULTIPLY = new Operator('*', 2, 1) {
        @Override
        public double calculate(double val1, double val2) {
            return val1 * val2;
        }
    },

    DIVIDE = new Operator('/', 2, 1) {
        @Override
        public double calculate(double val1, double val2) {
            return val1 / val2;
        }
    },

    MODULO = new Operator('%', 2, 1) {//FIXME neutral element
        @Override
        public double calculate(double val1, double val2) {
            return val1 % val2;
        }
    },

    POW = new Operator('^', 3, 1) {
        @Override
        public double calculate(double val1, double val2) {
            return Math.pow(val1, val2);
        }
    };

    public static final Set<Operator> DEFAULT_OPERATORS = new HashSet<>(
            Arrays.asList(PLUS, MINUS, MULTIPLY, DIVIDE, MODULO, POW));


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

    private static Operator[] values() {
        return DEFAULT_OPERATORS.toArray(new Operator[0]);
    }

    public static boolean isOperator(char c) {
        return Arrays.stream(values()).anyMatch(o -> o.symbol == c);
    }
}
