package de.ungefroren.adventofcode.y2020.day14;

import de.ungefroren.adventofcode.y2020.helpers.PuzzleInput;

import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day14A {
    private static final Pattern MEM_REGEX = Pattern.compile("mem\\[(?<address>\\d+)] *= *(?<value>\\d+)");

    public static void main(String[] args) throws IOException {
        Bitmask mask = Bitmask.XXX;
        HashMap<Integer, Long> mem = new HashMap<>();
        for (String line : PuzzleInput.of(Day14A.class)) {
            if (line.startsWith("mask")) {
                mask = Bitmask.from(line);
            } else {
                Matcher matcher = MEM_REGEX.matcher(line);
                if (!matcher.matches()) throw new IllegalArgumentException("invalid line: " + line);
                int address = Integer.parseInt(matcher.group("address"));
                long value = Long.parseLong(matcher.group("value"));
                value = mask.applyOnValue(value);
                mem.put(address, value);
            }
        }
        long sum = mem.values().stream().mapToLong(Long::longValue).sum();
        System.out.println(sum);
    }
}
