package de.ungefroren.adventofcode.y2020.day20;

import de.ungefroren.adventofcode.y2020.helpers.PuzzleInput;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("DuplicatedCode")
public class Day20A {

    /*
    My tests showed that only 4 tiles in the input match exactly 2 other tiles in any way.
    This must be the corners of the final square as only corners have two adjacent tiles.
    All other tiles have 3 or 4 adjacent tiles.
     */

    public static void main(String[] args) throws IOException {
        List<Tile> tiles = PuzzleInput.of(Day20A.class).split("\\n\\n").map(Tile::parse).collect(Collectors.toList());
        List<Tile> corners = tiles.stream().filter(t -> findMatchingTiles(t, tiles).size() == 2).collect(Collectors.toList());
        long result = corners.stream().mapToLong(Tile::getId).reduce(1, (a, b) -> a * b);
        System.out.println("Corners:" +
                "\n==========\n");
        corners.forEach(System.out::println);
        System.out.println("Result: " + result);
    }

    public static List<Tile> findMatchingTiles(Tile t, List<Tile> all) {
        List<Tile> matching = new ArrayList<>(4);
        for (Tile other : all) {
            if (other == t) continue;
            //unmodified
            if (t.matches(other) != null) matching.add(t);
            //mirrored
            Tile mir = other.flipHor();
            if (t.matches(mir) != null) matching.add(mir);
            //rotated 90°
            Tile rot = other.rotate90();
            if (t.matches(rot) != null) matching.add(rot);
            //rotated 90° and mirrored
            mir = rot.flipHor();
            if (t.matches(mir) != null) matching.add(mir);
            //rotated 180°
            rot = rot.rotate90();
            if (t.matches(rot) != null) matching.add(rot);
            //rotated 180° and mirrored
            mir = rot.flipHor();
            if (t.matches(mir) != null) matching.add(mir);
            //rotated 270°
            rot = rot.rotate90();
            if (t.matches(rot) != null) matching.add(rot);
            //rotated 270° and mirrored
            mir = rot.flipHor();
            if (t.matches(mir) != null) matching.add(mir);
            //no further checks needed as they are all covered by these 8
            //see: https://stackoverflow.com/a/23550668
        }
        return matching;
    }
}
