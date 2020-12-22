package de.ungefroren.adventofcode.y2020.day03;

import de.ungefroren.adventofcode.y2020.helpers.PuzzleInput;

public class TobogganMap {
    private final boolean[][] map;

    public TobogganMap(boolean[][] map) {
        this.map = map;
    }

    public static TobogganMap parse(PuzzleInput input) {
        int height = input.getLines().size();
        int width = input.getLines().get(0).length();
        boolean[][] map = new boolean[width][height];
        int x = 0, y = 0;
        for (String line : input) {
            for (char c : line.toCharArray()) {
                map[x][y] = (c == '#');
                x++;
            }
            x = 0;
            y++;
        }
        return new TobogganMap(map);
    }

    public boolean isTree(int x, int y) {
        return map[x % map.length][y];
    }

    public int getHeight() {
        return map[0].length;
    }
}
