package de.ungefroren.adventofcode.y2020.day15;

import de.ungefroren.adventofcode.y2020.helpers.PuzzleInput;

import java.io.IOException;
import java.util.HashMap;

public class Day15A {
    public static void main(String[] args) throws IOException {
        HashMap<Integer, Integer> numbers = new HashMap<>(); //key is a previous number, value the turn it occurred
        int i = 0;
        int previous = -1;
        for (int num : PuzzleInput.of(Day15A.class).split(",").getInts()) {
            if (previous != -1) numbers.put(previous, i);
            previous = num;
            i++;
        }
        for (; i < 2020; i++) {
            int spoken = numbers.containsKey(previous) ? i - numbers.get(previous) : 0;
            numbers.put(previous, i);
            previous = spoken;
        }
        System.out.println(previous);
    }
}
