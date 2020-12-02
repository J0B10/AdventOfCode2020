package de.ungefroren.adventofcode.y2020.day02;

import de.ungefroren.adventofcode.y2020.helpers.PuzzleInput;
import de.ungefroren.adventofcode.y2020.helpers.Timer;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Day02A {
    public static void main(String[] args) throws IOException {
        Timer timer = new Timer().start();
        long count = PuzzleInput.of(Day02A.class).map(PasswordPolicy::parse).filter(PasswordPolicy::isValidOldPolicy).count();
        System.out.printf("%.3fms%n", timer.elapsed(TimeUnit.MILLISECONDS));
        System.out.println(count);
    }
}
