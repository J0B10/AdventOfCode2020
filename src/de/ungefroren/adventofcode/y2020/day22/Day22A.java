package de.ungefroren.adventofcode.y2020.day22;

import de.ungefroren.adventofcode.y2020.helpers.PuzzleInput;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;

@SuppressWarnings("DuplicatedCode")
public class Day22A {
    public static void main(String[] args) throws IOException {
        PuzzleInput in = PuzzleInput.of(Day22A.class).split("\\n\\n");
        Queue<Integer> player1 = Arrays.stream(in.getLines().get(0).split("\\n"))
                .filter(s -> s.matches("\\d+"))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(LinkedList::new));
        Queue<Integer> player2 = Arrays.stream(in.getLines().get(1).split("\\n"))
                .filter(s -> s.matches("\\d+"))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(LinkedList::new));
        while (!player1.isEmpty() && !player2.isEmpty()) {
            if (player1.peek() > player2.peek()) {
                player1.add(player1.poll());
                player1.add(player2.poll());
            } else {
                player2.add(player2.poll());
                player2.add(player1.poll());
            }
        }
        Queue<Integer> winner = player1.isEmpty() ? player2 : player1;
        int i = winner.size();
        long result = 0;
        for (int card : winner) result += (long) card * i--;
        System.out.println(result);
    }
}
