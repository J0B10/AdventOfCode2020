package de.ungefroren.adventofcode.y2020.day12;

import de.ungefroren.adventofcode.y2020.helpers.PuzzleInput;

import java.io.IOException;

public class Day12A {
    public static void main(String[] args) throws IOException {
        Pos pos = new Pos(0,0);
        Direction facing = Direction.EAST;
        for (String s : PuzzleInput.of(Day12A.class)) {
            char instruction = s.charAt(0);
            int value = Integer.parseInt(s.substring(1));
            switch (instruction) {
                case 'L':
                    facing = facing.plus(-value);
                    break;
                case 'R':
                    facing = facing.plus(value);
                    break;
                case 'F':
                    pos = pos.move(facing, value);
                    break;
                default:
                    pos = pos.move(Direction.valueOf(instruction), value);
            }
        }
        int distance = Math.abs(pos.x) + Math.abs(pos.y);
        System.out.println(distance);
    }
}
