package de.ungefroren.adventofcode.y2020.day04;

import de.ungefroren.adventofcode.y2020.helpers.PuzzleInput;

import java.io.IOException;
import java.util.HashMap;

public class Day04A {
    public static void main(String[] args) throws IOException {
        int sum = 0;
        for (String passport : PuzzleInput.of(Day04A.class).toString().split("\\n\\s*\\n")) {
            HashMap<String, String> map = new HashMap<>();
            for (String s : passport.split("[\\s\\n]")) {
                String[] kv = s.split(":");
                map.put(kv[0], kv[1]);
            }
            if (map.containsKey("byr") && map.containsKey("iyr")
                    && map.containsKey("eyr") && map.containsKey("hgt") && map.containsKey("hgt")
                    && map.containsKey("hcl") && map.containsKey("ecl") && map.containsKey("pid")) {
                sum++;
            }
        }
        System.out.println(sum);
    }
}
