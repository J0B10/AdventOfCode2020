package de.ungefroren.adventofcode.y2020.day07;

import de.ungefroren.adventofcode.y2020.helpers.PuzzleInput;

import java.io.IOException;
import java.util.Map;

public class Day07B {
        public static void main(String[] args) throws IOException {
            PuzzleInput input = PuzzleInput.of(Day07B.class);
            Map<String,LuggageBag> bags =  LuggageBag.parseAll(input);
            long inside = bags.get("shiny gold").getAmountInside();
            System.out.println(inside);
        }
}
