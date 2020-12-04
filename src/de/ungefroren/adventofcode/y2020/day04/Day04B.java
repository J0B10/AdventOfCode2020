package de.ungefroren.adventofcode.y2020.day04;

import de.ungefroren.adventofcode.y2020.helpers.PuzzleInput;

import java.io.IOException;
import java.util.HashMap;

public class Day04B {
    public static void main(String[] args) throws IOException {
        int sum = 0;
        for (String passport : PuzzleInput.of(Day04B.class).toString().split("\\n\\s*\\n")) {
            HashMap<String, String> map = new HashMap<>();
            for (String s : passport.split("[\\s\\n]")) {
                String[] kv = s.split(":");
                map.put(kv[0], kv[1]);
            }
            if (isValidPassport(map)) sum++;
        }
        System.out.println(sum);
    }

    private static boolean isValidPassport(HashMap<String, String> map) {
        if (!map.containsKey("byr")) return false;
        if (!map.containsKey("iyr")) return false;
        if (!map.containsKey("eyr")) return false;
        if (!map.containsKey("hgt")) return false;
        if (!map.containsKey("hcl")) return false;
        if (!map.containsKey("ecl")) return false;
        if (!map.containsKey("pid")) return false;
        try {
            int byr = Integer.parseInt(map.get("byr"));
            if (byr < 1920 || byr > 2002) return false;
            int iyr = Integer.parseInt(map.get("iyr"));
            if (iyr < 2010 || iyr > 2020) return false;
            int eyr = Integer.parseInt(map.get("eyr"));
            if (eyr < 2020 || eyr > 2030) return false;
            if (map.get("pid").length() != 9) return false;
            if (!map.get("hcl").matches("#[0-9a-f]{6}")) return false;
            String ecl = map.get("ecl");
            if (!ecl.equals("amb") && !ecl.equals("blu") && !ecl.equals("brn") && !ecl.equals("gry")
                    && !ecl.equals("grn") && !ecl.equals("hzl") && !ecl.equals("oth")) return false;
            String hgt = map.get("hgt");
            if (hgt.endsWith("cm")) {
                int cm = Integer.parseInt(hgt.replace("cm", ""));
                return cm >= 150 && cm <= 193;
            } else if (hgt.endsWith("in")) {
                int in = Integer.parseInt(hgt.replace("in", ""));
                return in >= 59 && in <= 76;
            } else {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
    }


}
