package de.ungefroren.adventofcode.y2020.day14;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Bitmask {


    public static final Bitmask XXX = new Bitmask(0, 0);
    public static final int LENGTH = 36;
    private final long defined;
    private final long values;

    private Bitmask(long defined, long values) {
        this.defined = defined;
        this.values = values;
    }

    public static Bitmask from(String s) {
        long defined = 0;
        long values = 0;
        if (s.startsWith("mask =")) s = s.substring(6).trim();
        for (int i = 0; i < LENGTH; i++) {
            long bit = 1L << i;
            try {
                switch (s.charAt(s.length() - i - 1)) {
                    case '1': //defined as 1, set defined bit to 1 but and value bit to 1
                        defined += bit;
                        values += bit;
                        break;
                    case '0': //defined as 0, set defined bit to 1 but leave value bit 0
                        defined += bit;
                        break;
                    case 'X':
                        break; //undefined, set neither defined nor value bit as it ain't defined
                    default:
                        throw new IllegalArgumentException("invalid mask: " + s);
                }
            } catch (IndexOutOfBoundsException e) {
                throw new IllegalArgumentException("invalid mask: " + s);
            }
        }
        return new Bitmask(defined, values);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = LENGTH - 1; i >= 0; i--) {
            long bit = 1L << i;
            if ((defined & bit) != 0) {
                if ((values & bit) != 0) s.append('1');
                else s.append('0');
            } else {
                s.append('X');
            }
        }
        return s.toString();
    }

    public long applyOnValue(long value) {
        long mask1s = (defined & values);
        long mask0s = (defined & ~values);
        return (value | mask1s) & ~mask0s;
    }

    public Set<Long> applyOnAddress(long value) {
        Set<Long> addresses = new HashSet<>();
        long mask1s = (defined & values);
        value = value | mask1s;
        addresses.add(value);
        LinkedList<Long> xBits = new LinkedList<>();
        for (int i = 0; i < LENGTH; i++) {
            long bit = 1L << i;
            if ((defined & bit) == 0) xBits.add(bit);
        }
        List<Long> values = applyXBit(xBits, new LinkedList<>(addresses));
        addresses.addAll(values);
        return addresses;
    }

    private List<Long> applyXBit(LinkedList<Long> xBits, List<Long> values) {
        if (xBits.isEmpty()) return values;
        List<Long> out = new LinkedList<>(values);
        long xBit = xBits.remove();
        for (long l : values) {
            out.add(l | xBit);
            out.add(l & ~xBit);
        }
        return applyXBit(xBits, out);
    }
}
