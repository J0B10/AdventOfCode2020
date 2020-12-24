package de.ungefroren.adventofcode.y2020.day24;

import de.ungefroren.adventofcode.y2020.helpers.PuzzleInput;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@SuppressWarnings({"unchecked", "DuplicatedCode"})
public class Day24B {
    public static void main(String[] args) throws IOException {
        HashMap<Pos, Boolean> bestagons = parseBestagons(PuzzleInput.of(Day24B.class));
        for (int i = 0; i < 100; i++) {
            final HashMap<Pos, Boolean> copygons = (HashMap<Pos, Boolean>) bestagons.clone();
            copygons.forEach((pos, black) -> {
                int adjacent = adjacent(pos, copygons);
                if (black && (adjacent == 0 || adjacent > 2)) bestagons.put(pos, false);
                if (black) {
                    listAdjacent(pos).stream()
                            .filter(adj -> !copygons.getOrDefault(adj, false))
                            .filter(white -> adjacent(white, copygons) == 2)
                            .forEach(p -> bestagons.put(p, true));
                }
            });
        }
        long black = bestagons.values().stream().filter(Boolean::booleanValue).count();
        System.out.println(black);
    }

    public static int adjacent(Pos p, HashMap<Pos, Boolean> hexagons) {
        return (int) listAdjacent(p).stream()
                .map(adj -> hexagons.getOrDefault(adj, false))
                .filter(Boolean::booleanValue)
                .count();
    }

    public static Set<Pos> listAdjacent(Pos p) {
        Set<Pos> adjacagons = new HashSet<>();
        adjacagons.add(new Pos(p.x - 1, p.y));
        adjacagons.add(new Pos(p.x + 1, p.y));
        if (p.y % 2 == 0) {
            adjacagons.add(new Pos(p.x, p.y + 1));
            adjacagons.add(new Pos(p.x - 1, p.y + 1));
            adjacagons.add(new Pos(p.x, p.y - 1));
            adjacagons.add(new Pos(p.x - 1, p.y - 1));
        } else {
            adjacagons.add(new Pos(p.x + 1, p.y + 1));
            adjacagons.add(new Pos(p.x, p.y + 1));
            adjacagons.add(new Pos(p.x + 1, p.y - 1));
            adjacagons.add(new Pos(p.x, p.y - 1));

        }
        return adjacagons;
    }

    public static HashMap<Pos, Boolean> parseBestagons(PuzzleInput input) {
        HashMap<Pos, Boolean> bestagons = new HashMap<>(); //https://youtu.be/thOifuHs6eY
        for (String line : input) {
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
            bestagons.put(p, !bestagons.getOrDefault(p, false));
        }
        return bestagons;
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
            return "Pos [ " + x + " ; " + y + " ]";
        }
    }
}
