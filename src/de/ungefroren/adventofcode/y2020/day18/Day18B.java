package de.ungefroren.adventofcode.y2020.day18;

import de.ungefroren.adventofcode.y2020.day18.tokens.Token;
import de.ungefroren.adventofcode.y2020.helpers.PuzzleInput;
import de.ungefroren.adventofcode.y2020.helpers.Timer;

import java.io.IOException;

public class Day18B {
    public static void main(String[] args) throws IOException {
        Operator.DEFAULT_OPERATORS.clear();
        Operator.DEFAULT_OPERATORS.add(new Operator('+', 2, 0) {
            @Override
            public double calculate(double val1, double val2) {
                return val1 + val2;
            }
        });
        Operator.DEFAULT_OPERATORS.add(new Operator('*', 1, 1) {
            @Override
            public double calculate(double val1, double val2) {
                return val1 * val2;
            }
        });

        long sum = PuzzleInput.of(Day18B.class).lines().mapToLong(line -> {
            Token t = Tokenizer.tokenize(line);
            long l = (long) t.resolve();
            System.out.printf("%8d=%s%n", l, t);
            return l;
        }).sum();
        System.out.println(sum);
    }
}
