package de.ungefroren.adventofcode.y2020.day09;

import de.ungefroren.adventofcode.y2020.helpers.PuzzleInput;

import java.io.IOException;

public class Day09A {
    public static void main(String[] args) throws IOException {
        XMASEncoding encoding = new XMASEncoding(25);
        for (long l : PuzzleInput.of(Day09A.class).getLongs()) {
            if (!encoding.add(l)) {
                System.out.println(l);
                return;
            }
        }
    }
}
