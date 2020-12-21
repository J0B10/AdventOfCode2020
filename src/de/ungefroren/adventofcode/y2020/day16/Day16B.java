package de.ungefroren.adventofcode.y2020.day16;

import de.ungefroren.adventofcode.y2020.helpers.PuzzleInput;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

        Map<Rule, Set<Integer>> indices = new HashMap<>();
        rules.forEach(rule -> {
            Set<Integer> is = IntStream.range(0, myTicket.values.size())
                    .filter(i -> valid.stream().allMatch(t -> rule.isValid(t.values.get(i))))
                    .boxed()
                    .collect(Collectors.toSet());
            indices.put(rule, is);
        });

        int knownSize = 0;
        Map<Rule, Integer> known;
        while (true) {
            known = indices.entrySet().stream().filter(e -> e.getValue().size() == 1)
                    .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().iterator().next()));
            if (knownSize == known.size()) break;
            knownSize = known.size();
            known.forEach((rule, index) -> indices.forEach((r, i) -> {
                if (!r.equals(rule)) i.remove(index);
            }));
        }
        if (known.size() != indices.size()) throw new RuntimeException("not all indices are known");
        long val = 1;
        for (Map.Entry<Rule, Integer> entry : known.entrySet()) {
            Rule rule = entry.getKey();
            Integer index = entry.getValue();
            if (rule.lbl.startsWith("departure ")) {
                val *= myTicket.values.get(index);
            }
            System.out.println(rule.lbl + ": " + myTicket.values.get(index));
        }
        System.out.println(val);
    }
}
