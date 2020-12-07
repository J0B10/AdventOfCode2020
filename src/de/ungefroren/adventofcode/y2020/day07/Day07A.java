package de.ungefroren.adventofcode.y2020.day07;

import de.ungefroren.adventofcode.y2020.helpers.PuzzleInput;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Day07A {
    public static void main(String[] args) throws IOException {
        PuzzleInput input = PuzzleInput.of(Day07A.class);
        Set<String> outermost = input.lines().map(line -> {
            int i = line.indexOf(" bags");
            return line.substring(0, i);
        }).collect(Collectors.toSet());
        Map<String,LuggageBag> bags =  LuggageBag.parseAll(input);
        long amount = bags.get("shiny gold").getAllContainers().stream()
                .filter(bag -> outermost.contains(bag.getColor())).count();
        System.out.println(amount);
    }
}
