package de.ungefroren.adventofcode.y2020.day05;

import de.ungefroren.adventofcode.y2020.helpers.PuzzleInput;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day05B {
    public static void main(String[] args) throws IOException {
        Set<Integer> seats = PuzzleInput.of(Day05A.class).lines().map(Day05A::seatID).collect(Collectors.toSet());
        int j = IntStream.range(0,128 * 8)
                .filter(i -> seats.contains(i - 1) && !seats.contains(i) && seats.contains(i + 1))
                .findAny()
                .getAsInt();
        System.out.println(j);
    }
}
