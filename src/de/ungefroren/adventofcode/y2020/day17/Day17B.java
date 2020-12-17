package de.ungefroren.adventofcode.y2020.day17;

import de.ungefroren.adventofcode.y2020.helpers.PuzzleInput;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@SuppressWarnings("DuplicatedCode")
public class Day17B {
    public static void main(String[] args) throws IOException {
        HashMap<Coordinates, Boolean> states = new HashMap<>();
        Bounds bounds = new Bounds();
        int x = 0, y = 0;
        for (String line : PuzzleInput.of(Day17B.class)) {
            for (char c : line.toCharArray()) {
                Coordinates coords = new Coordinates(x, y, 0);
                states.put(coords, c == '#');
                bounds.update(coords);
                x++;
            }
            x = 0;
            y++;
        }
        printMap(states, bounds);
        for (int i = 1; i <= 6; i++) {
            applyCycle(states, bounds);
            System.out.println("After " + i + " cycles:");
            printMap(states, bounds);
        }
        System.out.println("Result: " + states.entrySet().stream().filter(Map.Entry::getValue).count());
    }

    public static void applyCycle(HashMap<Coordinates, Boolean> states, Bounds bounds) {
        HashMap<Coordinates, Boolean> copy = new HashMap<>();
        for (int z = bounds.getMinZ() - 1; z <= bounds.getMaxZ() + 1; z++) {
            for (int y = bounds.getMinZ() - 1; y <= bounds.getMaxY() + 1; y++) {
                for (int x = bounds.getMinX() - 1; x <= bounds.getMaxX() + 1; x++) {
                    for (int w = bounds.getMinW() - 1; w <= bounds.getMaxW() + 1; w++) {
                        Coordinates i = new Coordinates(x, y, z, w);
                        boolean state = apply(i, states);
                        copy.put(i, state);
                        if (state) bounds.update(i);
                    }
                }
            }
        }
        states.clear();
        states.putAll(copy);
    }

    public static boolean apply(Coordinates pos, HashMap<Coordinates, Boolean> states) {
        int sum = 0;
        for (int z = -1; z <= 1; z++) {
            for (int y = -1; y <= 1; y++) {
                for (int x = -1; x <= 1; x++) {
                    for (int w = -1; w <= 1; w++) {
                        Coordinates i = pos.plus(x, y, z, w);
                        if (i.equals(pos)) continue;
                        Boolean val = states.get(i);
                        if (val != null && val) sum++;
                    }
                }
            }
        }
        Boolean val = states.get(pos);
        if (val != null && val) {
            return sum == 2 || sum == 3;
        } else {
            return sum == 3;
        }
    }

    public static void printMap(HashMap<Coordinates, Boolean> states, Bounds bounds) {
        for (int w = bounds.getMinW(); w <= bounds.getMaxW() ; w++) {
            for (int z = bounds.getMinZ(); z <= bounds.getMaxZ(); z++) {
                System.out.println();
                System.out.println("Z=" + z + " W=" + w);
                for (int y = bounds.getMinY(); y <= bounds.getMaxY(); y++) {
                    for (int x = bounds.getMinX(); x <= bounds.getMaxX(); x++) {
                        Boolean state = states.get(new Coordinates(x, y, z));
                        if (state != null && state) {
                            System.out.print('#');
                        } else {
                            System.out.print('.');
                        }
                    }
                    System.out.println();
                }
            }
        }
    }
}
