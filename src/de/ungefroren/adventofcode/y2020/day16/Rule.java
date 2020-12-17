package de.ungefroren.adventofcode.y2020.day16;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Rule {

    private static final Pattern REGEX =
            Pattern.compile("(?<lbl>.+): (?<minA>\\d+)-(?<maxA>\\d+) or (?<minB>\\d+)-(?<maxB>\\d+)");

    public final String lbl;
    public final int minA;
    public final int maxA;
    public final int minB;
    public final int maxB;

    private Rule(String lbl, int minA, int maxA, int minB, int maxB) {
        this.lbl = lbl;
        this.minA = minA;
        this.maxA = maxA;
        this.minB = minB;
        this.maxB = maxB;
    }

    public static Rule from(String s) {
        Matcher m = REGEX.matcher(s.trim());
        if (!m.matches()) throw new IllegalArgumentException("invalid rule - " + s);
        return new Rule(
                m.group("lbl"),
                Integer.parseInt(m.group("minA")),
                Integer.parseInt(m.group("maxA")),
                Integer.parseInt(m.group("minB")),
                Integer.parseInt(m.group("maxB"))
        );
    }

    public boolean isValid(int i) {
        boolean b = minA <= i && i <= maxA || minB <= i && i <= maxB;
        return b;
    }

    @Override
    public String toString() {
        return lbl + ": " + minA + '-' + maxA + " or " + minB + '-' + maxB;
    }
}
