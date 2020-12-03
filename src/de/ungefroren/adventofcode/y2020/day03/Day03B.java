package de.ungefroren.adventofcode.y2020.day03;

import de.ungefroren.adventofcode.y2020.helpers.PuzzleInput;
import de.ungefroren.adventofcode.y2020.helpers.Timer;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Day03B {
    public static void main(String[] args) throws IOException {
        Timer t = new Timer().start();
        TobogganMap map = TobogganMap.parse(PuzzleInput.of(Day03B.class));
        int sum1 = 0, sum2 = 0, sum3 = 0, sum4 = 0, sum5 = 0;
        for (int i = 1; i < map.getHeight(); i++) {
            if (map.isTree(i , i)) sum1++;
            if (map.isTree(i * 3, i)) sum2++;
            if (map.isTree(i * 5, i)) sum3++;
            if (map.isTree(i * 7, i)) sum4++;
            if (i * 2 < map.getHeight() && map.isTree(i, i * 2)) sum5++;
        }
        System.out.printf("%.3fms%n", t.elapsed(TimeUnit.MILLISECONDS));
        System.out.println(sum1 * sum2 * sum3 * sum4 * sum5);
    }
}
