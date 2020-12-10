package de.ungefroren.adventofcode.y2020.day10;

import de.ungefroren.adventofcode.y2020.helpers.PuzzleInput;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Day10B {

    private static final HashMap<Integer, Long> branches = new HashMap<>();

    /*
    How to:

    Inputs only have rating differences of 1 or 3.
    A rating difference of 3 between two adapters means there is only a single path between them.
    So this process can be optimized by counting how many steps of 1 directly follow each other and just counting the
    possible branches for this interval. This can then be saved and later reused.

    Then for all these intervals the branches are multiplied.

     */

    public static void main(String[] args) throws IOException {
        List<Integer> ratings = PuzzleInput.of(Day10B.class).getInts().stream().sorted().collect(Collectors.toList());
        int series = 0;
        int previous = 0;
        long branches_total = 1;
        for (int i : ratings) {
            if (i - previous == 1) {
                series++;
            } else if (series != 0){
                branches_total *= getAmountOfBranches(series);
                series = 0;
            }
            previous = i;
        }
        if (series != 0){
            branches_total *= getAmountOfBranches(series);
        }
        System.out.println(branches_total);
    }

    private static long getAmountOfBranches(int steps) {
        if (!branches.containsKey(steps)) {
            branches.put(steps, countBranches(0, steps));
        }
        return branches.get(steps);
    }

    private static long countBranches(int value, int goal) {
        if (value == goal) return 1;
        if (value > goal) return 0;
        long count = 0;
        for (int i = 1; i <= 3; i++) {
                count += countBranches(value + i, goal);
        }
        return count;
    }
}
