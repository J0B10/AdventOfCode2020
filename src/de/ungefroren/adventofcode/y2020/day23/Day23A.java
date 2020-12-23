package de.ungefroren.adventofcode.y2020.day23;

import de.ungefroren.adventofcode.y2020.helpers.PuzzleInput;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("DuplicatedCode")
public class Day23A {

    public static void main(String[] args) throws IOException {
        LinkedList<Integer> cups = PuzzleInput.of(Day23A.class).toString().chars()
                .map(c -> Character.digit(c, 10)).boxed().collect(Collectors.toCollection(LinkedList::new));
        for (int i = 0; i < 100; i++) move(cups);
        int one = cups.indexOf(1);
        for (int i = 1; i < cups.size(); i++) {
            System.out.print(cups.get((one + i) % cups.size()));
        }
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
}
