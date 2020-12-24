package de.ungefroren.adventofcode.y2020.day24;

import de.ungefroren.adventofcode.y2020.helpers.PuzzleInput;

import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;

@SuppressWarnings("DuplicatedCode")
public class Day24A {
    public static void main(String[] args) throws IOException {
        HashMap<Pos, Boolean> bestagons = new HashMap<>(); //https://youtu.be/thOifuHs6eY
        for (String line: PuzzleInput.of(Day24A.class)) {
            Optional<Character> last = Optional.empty();
            int x = 0, y = 0;
            for (char c : line.toCharArray()) {
                switch (c) {
                    case ('n'):
                    case ('s'):
                        last = Optional.of(c);
                        break;
                    case 'e':
                    case 'w':
                        String in = last.map(cHar -> cHar + "").orElse("") + c;
                        switch (in) {
                            case "e":
                                x++;
                                break;
                            case "se":
                                if (y % 2 != 0) x++;
                                y--;
                                break;
                            case "sw":
                                if (y % 2 == 0) x--;
                                y--;
                                break;
                            case "w":
                                x--;
                                break;
                            case "nw":
                                if (y % 2 == 0) x--;
                                y++;
                                break;
                            case "ne":
                                if (y % 2 != 0) x++;
                                y++;
                                break;
                        }
                        last = Optional.empty();
                        break;
                    default:
                        throw new IllegalArgumentException("invalid input: " + c);
                }
            }
            Pos p = new Pos(x, y);
            bestagons.put(p, !bestagons.getOrDefault(new Pos(x,y), false));
        }
        long black = bestagons.values().stream().filter(Boolean::booleanValue).count();
        System.out.println(black);
    }

    public static class Pos {
        public final int x;
        public final int y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pos pos = (Pos) o;
            return x == pos.x && y == pos.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return "Pos [ "  + x + " ; " + y + " ]";
        }
    }
}
