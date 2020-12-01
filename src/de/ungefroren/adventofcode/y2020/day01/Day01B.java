package de.ungefroren.adventofcode.y2020.day01;

import de.ungefroren.adventofcode.y2020.helpers.PuzzleInput;
import de.ungefroren.adventofcode.y2020.helpers.Timer;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Day01B {
    public static void main(String[] args) throws IOException {
        Timer timer = new Timer().start();
        Set<Integer> values = new HashSet<>(PuzzleInput.of(Day01B.class).getInts());
        for (int i : values) {
            for (int j : values) {
                int k = 2020 - i - j;
                if (values.contains(k)) {
                    timer.logElapsed();
                    System.out.printf("%d + %d + %d = 2020%n", i, j, k);
                    System.out.printf("%d * %d * %d = %d%n", i, j, k, i * j * k);
                    return;
                }
            }
        }
    }
}
