package de.ungefroren.adventofcode.y2020.day09;

import de.ungefroren.adventofcode.y2020.helpers.PuzzleInput;

import java.io.IOException;

public class Day09B {
    public static void main(String[] args) throws IOException {
        XMASEncoding encoding = new XMASEncoding(25);
        long weakness = encoding.findEncryptionWeakness(PuzzleInput.of(Day09B.class).getLongs());
        System.out.println(weakness);
    }
}
