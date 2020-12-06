package de.ungefroren.adventofcode.y2020.day06;

import de.ungefroren.adventofcode.y2020.helpers.PuzzleInput;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day06A {
    public static void main(String[] args) throws IOException {
        long sum = PuzzleInput.of(Day06A.class).split("\\n\\n").lines().mapToLong(group -> {
            Set<Integer> answers = group.chars().boxed().collect(Collectors.toSet());
            return IntStream.range('a', 'z' + 1).filter(answers::contains).count();
        }).sum();
        System.out.println(sum);
    }
}
