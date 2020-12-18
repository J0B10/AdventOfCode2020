package de.ungefroren.adventofcode.y2020.day18;

import de.ungefroren.adventofcode.y2020.day18.tokens.Token;
import de.ungefroren.adventofcode.y2020.helpers.PuzzleInput;

import java.io.IOException;
public class Day18A {
    public static void main(String[] args) throws IOException {
        long sum = PuzzleInput.of(Day18A.class).lines().mapToLong(line -> {
            Token t = Tokenizer.tokenize(line);
            long l = (long) t.resolve();
            System.out.printf("%8d=%s%n", l, t);
            return l;
        }).sum();
        System.out.println(sum);
    }
}
