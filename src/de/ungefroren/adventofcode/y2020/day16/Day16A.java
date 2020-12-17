package de.ungefroren.adventofcode.y2020.day16;

import de.ungefroren.adventofcode.y2020.helpers.PuzzleInput;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day16A {
    public static void main(String[] args) throws IOException {
        PuzzleInput input = PuzzleInput.of(Day16A.class).split("\\n\\n");
        List<Rule> rules = Arrays.stream(input.getLines().get(0).split("\\n"))
                .map(Rule::from).collect(Collectors.toList());
        List<Integer> values = Arrays.stream(input.getLines().get(2).substring("nearby tickets:\n".length()).split("[,\\n]"))
                .map(Integer::parseInt).collect(Collectors.toList());
        long error_rate = values.stream().filter(i ->
                rules.stream().noneMatch(rule -> rule.isValid(i))
        ).mapToLong(Long::new).sum();
        System.out.println(error_rate);
    }
}
