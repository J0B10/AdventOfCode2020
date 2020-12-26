package de.ungefroren.adventofcode.y2020.day25;

import de.ungefroren.adventofcode.y2020.helpers.PuzzleInput;

import java.io.IOException;

public class Day25A {

    public static void main(String[] args) throws IOException {
        PuzzleInput in = PuzzleInput.of(Day25A.class);
        int cardKey = in.getInts().get(0);
        int doorKey = in.getInts().get(1);
        long val = 1;
        int doorLoopSize = 0;
        int cardLoopSize = 0;
        for (int i = 1; i < Integer.MAX_VALUE; i++) {
            val = transform(val, 7);
            if (val == cardKey) {
                cardLoopSize = i;
            }
            if (val == doorKey) {
                doorLoopSize = i;
            }
            if (doorLoopSize != 0 && cardLoopSize != 0) break;
        }
        System.out.println("door loop size: " + doorLoopSize);
        System.out.println("card loop size: " + cardLoopSize);
        val = 1;
        for (int i = 1; i <= doorLoopSize; i++) val = transform(val, cardKey);
        System.out.println("encryption key: " + val);
    }

    public static long transform(long val, int subjectNumber) {
        val = val * subjectNumber;
        val = val % 20201227;
        return val;
    }
}
