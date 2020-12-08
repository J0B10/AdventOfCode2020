package de.ungefroren.adventofcode.y2020.day08;

import de.ungefroren.adventofcode.y2020.helpers.PuzzleInput;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Day08B {
    public static void main(String[] args) throws IOException {
        Assembler assembler = null;
        List<String> instructions = PuzzleInput.of(Day08B.class).getLines();
        for (int i = 0; i < instructions.size(); i++) {
            String instruction = instructions.get(i);
            if (instruction.startsWith("jmp")) {
                String[] workingCopy = instructions.toArray(new String[0]);
                workingCopy[i] = instruction.replace("jmp", "nop");
                assembler = new Assembler(Arrays.stream(workingCopy));
                assembler.run();
                if (!assembler.abortedAbnormally()) break;
            } else if (instruction.startsWith("nop")) {
                String[] workingCopy = instructions.toArray(new String[0]);
                workingCopy[i] = instruction.replace("nop", "jmp");
                assembler = new Assembler(Arrays.stream(workingCopy));
                assembler.run();
                if (!assembler.abortedAbnormally()) break;
            }
        }
        System.out.println(assembler.getAccumulator());
    }
}
