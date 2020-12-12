package de.ungefroren.adventofcode.y2020.day12;

import de.ungefroren.adventofcode.y2020.helpers.PuzzleInput;

import java.io.IOException;

@SuppressWarnings("DuplicatedCode")
public class Day12B {
    public static void main(String[] args) throws IOException {
        Pos ship = new Pos(0, 0); // the ships position relative to its origin
        Pos pos = new Pos(10, 1); //the waypoints position relative to the ship
        for (String s : PuzzleInput.of(Day12B.class)) {
            char instruction = s.charAt(0);
            int value = Integer.parseInt(s.substring(1));
            switch (instruction) {
                case 'R':
                    value = 360 - value;
                case 'L':
                    double rot = (value * 2 * Math.PI) / 360;
                    int x = (int) Math.round(pos.x * Math.cos(rot) - pos.y * Math.sin(rot));
                    int y = (int) Math.round(pos.y * Math.cos(rot) + pos.x * Math.sin(rot));
                    pos = new Pos(x, y);
                    break;
                case 'F':
                    ship = new Pos(ship.x + pos.x * value, ship.y + pos.y * value);
                    break;
                default:
                    pos = pos.move(Direction.valueOf(instruction), value);
            }
        }
        int distance = Math.abs(ship.x) + Math.abs(ship.y);
        System.out.println(distance);
    }

}
