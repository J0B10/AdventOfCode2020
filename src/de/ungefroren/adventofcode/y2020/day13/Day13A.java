package de.ungefroren.adventofcode.y2020.day13;

import de.ungefroren.adventofcode.y2020.helpers.PuzzleInput;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day13A {
    public static void main(String[] args) throws IOException {
        List<String> lines = PuzzleInput.of(Day13A.class).getLines();
        int departure = Integer.parseInt(lines.get(0));
        List<Integer> buses = Arrays.stream(lines.get(1).split(","))
                .filter(s -> s.matches("\\d+")).map(Integer::parseInt).collect(Collectors.toList());
        int t = departure;
        while (true) {
            for (int bus : buses) {
                if (t % bus == 0) {
                    System.out.println("Earliest departure:");
                    System.out.printf("timestamp: %d%n", t);
                    System.out.printf("Bus ID: %8d%n", bus);
                    System.out.printf("Result: %8d%n", (t - departure) * bus);
                    return;
                }
            }
            t++;
        }
    }
}
