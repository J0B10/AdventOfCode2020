package de.ungefroren.adventofcode.y2020.day16;

import de.ungefroren.adventofcode.y2020.helpers.PuzzleInput;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day16B {
    public static void main(String[] args) throws IOException {
        PuzzleInput input = PuzzleInput.of(Day16B.class).split("\\n\\n");
        List<Rule> rules = Arrays.stream(input.getLines().get(0).split("\\n"))
                .map(Rule::from).collect(Collectors.toList());
        Ticket myTicket = Ticket.from(input.getLines().get(1).substring("your ticket:\n".length()));
        List<Ticket> nearby = Arrays.stream(input.getLines().get(2).substring("nearby tickets:\n".length()).split("\\n"))
                .map(Ticket::from).collect(Collectors.toList());
        List<Ticket> valid = nearby.stream().filter(t -> t.checkValidity(rules)).collect(Collectors.toList());

        long val = 1;
        for (Rule rule : rules/*.stream().filter(r -> r.lbl.startsWith("departure ")).collect(Collectors.toList())*/) {
            int index = IntStream.range(0, myTicket.values.size())
                    .filter(i ->
                            valid.stream().allMatch(t -> rule.isValid(t.values.get(i)))
                    ).findAny().getAsInt();
            if (rule.lbl.startsWith("departure ")) {
                val *= myTicket.values.get(index);
            }
            System.out.println(rule.lbl + ": " + myTicket.values.get(index));
        }
        System.out.println(val);
        //FIXME do not use same value twice
    }
}
