package de.ungefroren.adventofcode.y2020.day09;

import java.util.LinkedList;
import java.util.List;

public class XMASEncoding {
    private final LinkedList<Long> preamble = new LinkedList<>();
    private final int preambleSize;

    public XMASEncoding(int preambleSize) {
        this.preambleSize = preambleSize;
    }

    private void addToPreamble(long i) {
        if (isPreambleFull()) preamble.removeLast();
        preamble.addFirst(i);
    }

    private boolean isPreambleFull() {
        return preamble.size() >= preambleSize;
    }

    public boolean isValid(long i) {
        for (long val1 : preamble) {
            if (val1 * 2 == i) continue;
            if (preamble.contains(i - val1)) return true;
        }
        return false;
    }

    public boolean add(long i) {
        if (!isPreambleFull() || isValid(i)) {
            addToPreamble(i);
            return true;
        } else {
            return false;
        }
    }

    public void reset() {
        preamble.clear();
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public long findEncryptionWeakness(List<Long> inputs) {
        reset();
        long firstInvalid = -1;
        for (long l : inputs) {
            if (!add(l)) {
                firstInvalid = l;
                break;
            }
        }
        if (firstInvalid == -1)
            throw new IllegalArgumentException("input list must contain invalid numbers to find a encryption weakness");
        reset();
        for (long l : inputs) {
            preamble.addFirst(l);
            while (preamble.stream().mapToLong(Long::longValue).sum() > firstInvalid) {
                preamble.removeLast();
            }
            if (preamble.stream().mapToLong(Long::longValue).sum() == firstInvalid) {
                long min = preamble.stream().mapToLong(Long::longValue).min().getAsLong();
                long max = preamble.stream().mapToLong(Long::longValue).max().getAsLong();
                reset();
                return min + max;
            }
        }
        reset();
        throw new IllegalArgumentException("no encryption weakness found for that input");
    }
}
