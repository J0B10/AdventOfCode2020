package de.ungefroren.adventofcode.y2020.day01;

import de.ungefroren.adventofcode.y2020.helpers.PuzzleInput;
import de.ungefroren.adventofcode.y2020.helpers.Timer;

import java.util.HashSet;
import java.util.Set;

public class Day01A {
    public static void main(String[] args) throws Exception {
        Timer timer = new Timer().start();
        Set<Integer> values = new HashSet<>(PuzzleInput.of(Day01A.class).getInts());
        for (int i : values) {
            int j = 2020 - i;
            if (values.contains(j)) {
                timer.logElapsed();
                System.out.printf("%d + %d = 2020%n", i, j);
                System.out.printf("%d * %d = %d%n", i, j, i * j);
                break;
            }
        }
    }
}
