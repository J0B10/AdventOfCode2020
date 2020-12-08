package de.ungefroren.adventofcode.y2020.day08;

import de.ungefroren.adventofcode.y2020.helpers.PuzzleInput;

import java.io.IOException;

public class Day08A {
    public static void main(String[] args) throws IOException {
        Assembler assembler = new Assembler(PuzzleInput.of(Day08A.class).lines());
        assembler.run();
        System.out.println("Accumulator: " + assembler.getAccumulator());
    }
}
