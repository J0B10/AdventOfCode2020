package de.ungefroren.adventofcode.y2020.day05;

import de.ungefroren.adventofcode.y2020.helpers.PuzzleInput;

import java.io.IOException;

public class Day05A {
    public static void main(String[] args) throws IOException {
        int i = PuzzleInput.of(Day05A.class).lines().mapToInt(Day05A::seatID).max().getAsInt();
        System.out.println(i);
    }

    static int seatID(String s) {
        int rowMin = 0, rowMax = 127;
        int columnMin = 0, columnMax = 7;
        for (char c : s.toCharArray()) {
            switch (c) {
                case 'F':
                    rowMax = (rowMax - rowMin) / 2 + rowMin;
                    break;
                case 'B':
                    rowMin = (rowMax - rowMin) / 2 + rowMin + 1;
                    break;
                case 'L':
                    columnMax = (columnMax - columnMin) / 2 + columnMin;
                    break;
                case 'R':
                    columnMin = (columnMax - columnMin) / 2 + columnMin + 1;
                    break;
            }
        }
        if (rowMin != rowMax)
            throw new RuntimeException("rowMin != rowMax : " + rowMin + " != " + rowMax);
        if (columnMin != columnMax)
            throw new RuntimeException("columnMin != columnMax : " + columnMin + " != " + columnMax);
        return rowMin * 8 + columnMin;
    }
}
