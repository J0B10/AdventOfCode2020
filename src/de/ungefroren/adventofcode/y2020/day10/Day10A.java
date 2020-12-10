package de.ungefroren.adventofcode.y2020.day10;

import de.ungefroren.adventofcode.y2020.helpers.PuzzleInput;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class Day10A {
    public static void main(String[] args) throws IOException {
        int prev = 0;
        int d1 = 0, d3 = 0;
        List<Integer> ratings = PuzzleInput.of(Day10A.class).getInts().stream().sorted().collect(Collectors.toList());
        for (int i : ratings) {
            if (i - prev == 1) d1++;
            else if (i - prev == 3) d3++;
            prev = i;
        }
        System.out.println(d1 * ++d3);
    }
}
