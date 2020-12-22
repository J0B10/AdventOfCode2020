package de.ungefroren.adventofcode.y2020.day02;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordPolicy {

    private static final Pattern PARSE_REGEX = Pattern.compile("(\\d+)-(\\d+) ([a-z]): ([a-z]+)");

    private final int min;
    private final int max;
    private final char letter;
    private final String password;

    public PasswordPolicy(int min, int max, char letter, String password) {
        this.min = min;
        this.max = max;
        this.letter = letter;
        this.password = password;
    }

    public static PasswordPolicy parse(String string) {
        Matcher matcher = PARSE_REGEX.matcher(string);
        if (!matcher.find()) throw new IllegalArgumentException("Invalid password policy: " + string);
        int min = Integer.parseInt(matcher.group(1));
        int max = Integer.parseInt(matcher.group(2));
        char c = matcher.group(3).charAt(0);
        String password = matcher.group(4);
        return new PasswordPolicy(min, max, c, password);
    }

    public boolean isValidOldPolicy() {
        long occurrences = password.chars().filter(c -> c == letter).count();
        return occurrences >= min && occurrences <= max;
    }

    public boolean isValid() {
        if (max > password.length()) throw new IllegalArgumentException();
        boolean minMatches = password.charAt(min - 1) == letter;
        boolean maxMatches = password.charAt(max - 1) == letter;
        return minMatches ^ maxMatches;
    }

    @Override
    public String toString() {
        return String.format("%d-%d %c: %s", min, max, letter, password);
    }
}
