package de.ungefroren.adventofcode.y2020.day23;

import de.ungefroren.adventofcode.y2020.helpers.PuzzleInput;
import de.ungefroren.adventofcode.y2020.helpers.Timer;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("DuplicatedCode")
public class Day23B {

    public static void main(String[] args) throws IOException {
        LinkedList<Integer> cups = PuzzleInput.of(Day23B.class).toString().chars()
                .map(c -> Character.digit(c, 10)).boxed().collect(Collectors.toCollection(LinkedList::new));
        for (int i = cups.size() + 1; i < 1000000; i++) cups.addLast(i);
        Game g = new Game(cups.stream().mapToInt(Integer::intValue).toArray());
        Timer t = new Timer().start();
        for (int i = 0; i < 10000000; i++) g.move();
        t.logElapsed();

        int i = g.indexOf(1);
        System.out.println((long) g.get(i + 1) * g.get(i + 2));
    }

    @SuppressWarnings({"ConstantConditions"})
    private static void move(LinkedList<Integer> cups) {
        int current = cups.poll();
        List<Integer> pickedUp = new LinkedList<>();
        for (int i = 0; i < 3; i++) pickedUp.add(cups.poll());
        int dest = 0;
        int i = current - 1;
        boolean loop = true;
        while (loop) {
            dest = 0;
            int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
            for (int cup : cups) {
                if (cup == i) {
                    loop = false;
                    break;
                } else {
                    if (cup > max) max = cup;
                    else if (cup < min) min = cup;
                    dest++;
                }
            }
            if (i < min) i = max;
            else i--;
        }
        cups.addAll(dest + 1, pickedUp);
        cups.addLast(current);
    }

    public static class Game {

        private final int size;
        private final int[] values;
        private final int[] indices;
        private int currentIndex = 0;

        public Game(int[] values) {
            this.size = values.length;
            this.values = values.clone();
            this.indices = new int[size];
            for (int i = 0; i < size; i++) {
                int val = values[i];
                if (val == 0 || val > size) throw new IllegalArgumentException("invalid cup label: " + val);
                if (indices[val - 1] != 0) throw new IllegalArgumentException("cup label occurs twice: " + val);
                indices[val - 1] = i;
            }
        }

        public int get(int index) {
            if (index < 0) index = size - index;
            else if (index >= size) index = index % size;
            return values[index];
        }

        public int indexOf(int value) {
            return indices[value - 1];
        }

        private void set(int index, int value) {
            if (index < 0) index = size - index;
            else if (index >= size) index = index % size;
            values[index] = value;
            indices[value - 1] = index;
        }

        public void move() {
            int currentVal = get(currentIndex);
            int destVal = currentVal - 1;
            List<Integer> pickedUp = Arrays.asList(get(currentIndex + 1), get(currentIndex + 2), get(currentIndex + 3));
            while (pickedUp.contains(destVal)) destVal--;
            if (destVal <= 0) destVal = size;
            while (pickedUp.contains(destVal)) destVal--;
            int destIndex = indexOf(destVal);
            if (destIndex < currentIndex) destIndex = size + destIndex;
            for (int i = currentIndex + 4; i <= destIndex; i++) {
                int val = get(i);
                set(i - 3, val);
            }
            for (int i = 0; i < 3; i++) set(destIndex + i - 2, pickedUp.get(i));
            currentIndex = (currentIndex + 1) % size;
        }

        public int size() {
            return size;
        }
    }
}
