package de.ungefroren.adventofcode.y2020.day11;

import de.ungefroren.adventofcode.y2020.helpers.PuzzleInput;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Day11A {
    public static void main(String[] args) throws IOException {
        //horrible lambda to read input into lovely clean array
        Sitable[][] input = PuzzleInput.of(Day11A.class).map(row -> row.chars().mapToObj(c -> {
            if (c == 'L') return new Seat();
            else if (c == '.') return new Floor();
            else throw new IllegalArgumentException();
        }).toArray(Sitable[]::new)).toArray(Sitable[][]::new);

        boolean changes = true;
        while (changes) {
            //Debug options:
            ///printMap(input);
            //System.out.println();
            changes = false;
            Sitable[][] temp = copy(input);
            for (int row = 0; row < input.length; row++) {
                for (int sNr = 0; sNr < input[0].length; sNr++) {
                    Sitable seat = input[row][sNr];
                    if (seat instanceof Floor) continue; //Floor never changes

                    int sum = 0;
                    for (int l = row - 1; l <= row + 1 && l < input.length; l++) {
                        for (int i = sNr - 1; i <= sNr + 1 && i < input[0].length; i++) {
                            if (l < 0 || i < 0 || (l == row && i == sNr)) continue;
                            if (input[l][i].isOccupied()) sum++;
                        }
                    }

                    if (seat.isOccupied() && sum >= 4) {
                        temp[row][sNr] = new Seat(false);
                        changes = true;
                    } else if (!seat.isOccupied() && sum == 0) {
                        temp[row][sNr] = new Seat(true);
                        changes = true;
                    }
                }
            }
            input = temp;
        }

        long occupied = Arrays.stream(input).flatMap(Arrays::stream).filter(Sitable::isOccupied).count();
        System.out.println(occupied);
    }

    private static void printMap(Sitable[][] seats) {
        String s = Arrays.stream(seats).map(
                row -> Arrays.stream(row).map(Sitable::toString).collect(Collectors.joining())
        ).collect(Collectors.joining("\n"));
        System.out.println(s);
    }

    private static Sitable[][] copy(Sitable[][] sitables) {
        Sitable[][] clone = new Sitable[sitables.length][];
        for (int i = 0; i < sitables.length; i++) {
            clone[i] = Arrays.copyOf(sitables[i], sitables[i].length);
        }
        return clone;
    }
}
