package de.ungefroren.adventofcode.y2020.day22;

import de.ungefroren.adventofcode.y2020.helpers.PuzzleInput;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

@SuppressWarnings("DuplicatedCode")
public class Day22B {

    public static void main(String[] args) throws IOException {
        PuzzleInput in = PuzzleInput.of(Day22B.class).split("\\n\\n");
        LinkedList<Integer> player1 = Arrays.stream(in.getLines().get(0).split("\\n"))
                .filter(s -> s.matches("\\d+"))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(LinkedList::new));
        LinkedList<Integer> player2 = Arrays.stream(in.getLines().get(1).split("\\n"))
                .filter(s -> s.matches("\\d+"))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(LinkedList::new));
        Set<GameState> history = new HashSet<>();
        int w;
        do {
            w = recursiveCombat(player1, player2, history);
        } while (w != 1 && w != 2);
        Queue<Integer> winner = w == 1 ? player1 : player2;
        int i = winner.size();
        long result = 0;
        for (int card : winner) result += (long) card * i--;
        System.out.println(result);
    }

    @SuppressWarnings("ConstantConditions")
    private static int recursiveCombat(LinkedList<Integer> player1, LinkedList<Integer> player2, Set<GameState> history) {
        GameState state = new GameState(player1, player2);
        if (history.contains(state)) {
            return 1;
        } else {
            history.add(state);

            if (player1.isEmpty()) {
                return 2;
            }
            if (player2.isEmpty()) {
                return 1;
            }
            int player1Card = player1.poll();
            int player2Card = player2.poll();

            if (player1.size() < player1Card || player2.size() < player2Card) {
                if (player1Card < player2Card) {
                    player2.add(player2Card);
                    player2.add(player1Card);
                } else {
                    player1.add(player1Card);
                    player1.add(player2Card);
                }
            } else {
                Set<GameState> sGHistory = new HashSet<>();
                LinkedList<Integer> sGPlayer1 = new LinkedList<>(player1.subList(0, player1Card));
                LinkedList<Integer> sGPlayer2 = new LinkedList<>(player2.subList(0, player2Card));
                int winner;
                do {
                    winner = recursiveCombat(sGPlayer1, sGPlayer2, sGHistory);
                } while (winner != 1 && winner != 2);
                if (winner == 1) {
                    player1.add(player1Card);
                    player1.add(player2Card);
                } else {
                    player2.add(player2Card);
                    player2.add(player1Card);
                }
            }
            return -1;
        }
    }

    private static class GameState {
        public final int[] player1;
        public final int[] player2;

        public GameState(LinkedList<Integer> player1, LinkedList<Integer> player2) {
            this.player1 = player1.stream().mapToInt(Integer::intValue).toArray();
            this.player2 = player2.stream().mapToInt(Integer::intValue).toArray();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            GameState gameState = (GameState) o;
            return Arrays.equals(player1, gameState.player1) && Arrays.equals(player2, gameState.player2);
        }

        @Override
        public int hashCode() {
            int result = Arrays.hashCode(player1);
            result = 31 * result + Arrays.hashCode(player2);
            return result;
        }
    }
}
