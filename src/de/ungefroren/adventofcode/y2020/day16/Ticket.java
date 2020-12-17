package de.ungefroren.adventofcode.y2020.day16;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Ticket {
    public final List<Integer> values;

    public Ticket(List<Integer> values) {
        this.values = Collections.unmodifiableList(values);
    }

    public static Ticket from(String s) {
        List<Integer> values = Arrays.stream(s.split(",")).map(Integer::parseInt).collect(Collectors.toList());
        return new Ticket(values);
    }

    public boolean checkValidity(List<Rule> rules) {
        return values.stream().allMatch(i -> rules.stream().anyMatch(r -> r.isValid(i)));
    }

    @Override
    public String toString() {
        return values.stream().map(Object::toString).collect(Collectors.joining(","));
    }
}
