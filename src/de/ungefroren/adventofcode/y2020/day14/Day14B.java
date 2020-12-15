package de.ungefroren.adventofcode.y2020.day14;

import de.ungefroren.adventofcode.y2020.helpers.PuzzleInput;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day14B {
    private static final Pattern MEM_REGEX = Pattern.compile("mem\\[(?<address>\\d+)] *= *(?<value>\\d+)");

    public static void main(String[] args) throws IOException {
        Bitmask mask = Bitmask.XXX;
        HashMap<Long, Long> mem = new HashMap<>();
        for (String line : PuzzleInput.of(Day14B.class)) {
            if (line.startsWith("mask")) {
                mask = Bitmask.from(line);
            } else {
                Matcher matcher = MEM_REGEX.matcher(line);
                if (!matcher.matches()) throw new IllegalArgumentException("invalid line: " + line);
                int address = Integer.parseInt(matcher.group("address"));
                long value = Long.parseLong(matcher.group("value"));
                Set<Long> addresses = mask.applyOnAddress(address);
                addresses.forEach(a -> mem.put(a, value));
            }
        }
        BigDecimal sum = mem.values().stream().map(BigDecimal::new).reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println(sum);
    }
}
