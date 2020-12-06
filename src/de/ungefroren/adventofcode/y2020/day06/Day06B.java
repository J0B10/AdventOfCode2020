package de.ungefroren.adventofcode.y2020.day06;

import de.ungefroren.adventofcode.y2020.helpers.PuzzleInput;

import java.io.IOException;

public class Day06B {
    public static void main(String[] args) throws IOException {
        int sum = PuzzleInput.of(Day06A.class).split("\\n\\n").lines().mapToInt(group -> {
            String[] persons = group.split("\\n");
            int yes = 0;
            for (char c : persons[0].toCharArray()) {
                boolean all = true;
                for (int i = 1; i < persons.length; i++) {
                    if (persons[i].indexOf(c) == -1) {
                        all = false;
                        break;
                    }
                }
                if (all) yes++;
            }
            return yes;
        }).sum();
        System.out.println(sum);
    }
}
