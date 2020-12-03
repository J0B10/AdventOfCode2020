package de.ungefroren.adventofcode.y2020.day03;

import de.ungefroren.adventofcode.y2020.helpers.PuzzleInput;
import de.ungefroren.adventofcode.y2020.helpers.Timer;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Day03A {
    public static void main(String[] args) throws IOException {
        Timer t = new Timer().start();
        TobogganMap map = TobogganMap.parse(PuzzleInput.of(Day03A.class));
        int sum = 0;
        for (int i = 1; i < map.getHeight(); i++) {
            if (map.isTree(i * 3, i)) sum++;
        }
        System.out.printf("%.3fms%n", t.elapsed(TimeUnit.MILLISECONDS));
        System.out.println(sum);
    }
}
