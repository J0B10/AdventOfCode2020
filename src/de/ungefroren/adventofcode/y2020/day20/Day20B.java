package de.ungefroren.adventofcode.y2020.day20;

import de.ungefroren.adventofcode.y2020.helpers.PuzzleInput;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("DuplicatedCode")
public class Day20B {

    private static final String MONSTER = "                  # \n" +
            "#    ##    ##    ###\n" +
            " #  #  #  #  #  #   ";

    public static void main(String[] args) throws IOException {
        List<Tile> tiles = PuzzleInput.of(Day20B.class).split("\\n\\n").map(Tile::parse).collect(Collectors.toList());
        List<Tile> borders = tiles.stream().filter(t -> findMatchingTiles(t, tiles).size() == 3).collect(Collectors.toList());
        Tile start = findNWCorner(tiles);
        List<List<Tile>> lines = new ArrayList<>();
        List<Tile> line = new ArrayList<>();
        line.add(start);
        tiles.remove(start);
        Tile nxt, last = start;
        while (!tiles.isEmpty()) {
            while ((nxt = findMatchingTile(last, null, tiles)) != null) {
                final Tile nxtF = nxt;
                tiles.removeIf(t -> t.getId() == nxtF.getId());
                line.add(nxt);
                last = nxt;
            }
            lines.add(line);
            line = new ArrayList<>();
            nxt = findMatchingTile(null, start, tiles);
            if (nxt == null) break;
            final Tile nxtF = nxt;
            tiles.removeIf(t -> t.getId() == nxtF.getId());
            line.add(nxt);
            last = nxt;
            start = nxt;
        }
        Tile finaL = combine(lines);
        int monsters = 0;

        //unmodified
        monsters = countMonsters(finaL);
        if (monsters != 0) {
            System.out.println(waterRoughness(finaL));
            return;
        }
        //mirrored
        Tile mir = finaL.flipHor();
        monsters = countMonsters(mir);
        if (monsters != 0) {
            System.out.println(waterRoughness(mir));
            return;
        }
        //rotated 90°
        Tile rot = finaL.rotate90();
        monsters = countMonsters(rot);
        if (monsters != 0) {
            System.out.println(waterRoughness(rot));
            return;
        }
        //rotated 90° and mirrored
        mir = rot.flipHor();
        monsters = countMonsters(mir);
        if (monsters != 0) {
            System.out.println(waterRoughness(mir));
            return;
        }
        //rotated 180°
        rot = rot.rotate90();
        monsters = countMonsters(rot);
        if (monsters != 0) {
            System.out.println(waterRoughness(rot));
            return;
        }
        //rotated 180° and mirrored
        mir = rot.flipHor();
        monsters = countMonsters(mir);
        if (monsters != 0) {
            System.out.println(waterRoughness(mir));
            return;
        }
        //rotated 270°
        rot = rot.rotate90();
        monsters = countMonsters(rot);
        if (monsters != 0) {
            System.out.println(waterRoughness(rot));
            return;
        }
        //rotated 270° and mirrored
        mir = rot.flipHor();
        monsters = countMonsters(mir);
        System.out.println(monsters);
    }

    public static Tile combine(List<List<Tile>> tiles) {
        int size = (Tile.DEFAULT_SIZE - 2) * tiles.size();
        boolean[][] all = new boolean[size][size];
        int x = 0, y = 0;
        for (List<Tile> line : tiles) {
            for (Tile t : line) {
                boolean[][] content = t.getValues();
                for (int ty = 0; ty < Tile.DEFAULT_SIZE - 2; ty++) {
                    for (int tx = 0; tx < Tile.DEFAULT_SIZE - 2; tx++) {
                        all[x * (Tile.DEFAULT_SIZE - 2) + tx][y * (Tile.DEFAULT_SIZE - 2) + ty] = content[tx + 1][ty + 1];
                    }
                }
                x++;
            }
            x = 0;
            y++;
        }
        return new Tile(0, all);
    }

    public static List<Pos> monster() {
        List<Pos> p = new ArrayList<>();
        int x = 0, y = 0;
        for (String line : MONSTER.split("\\n")) {
            for (char c : line.toCharArray()) {
                if (c == '#') p.add(new Pos(x, y));
                x++;
            }
            x = 0;
            y++;
        }
        return p;
    }

    public static int countMonsters(Tile t) {
        int i = 0;
        boolean[][] val = t.getValues();
        for (int y = 0; y < val.length; y++) {
            for (int x = 0; x < val.length; x++) {
                if (containsMonster(x, y, t)) i++;
            }
        }
        return i;
    }

    public static long waterRoughness(Tile t) {
        int monsterLen = monster().size();
        long sum = 0;
        boolean[][] val = t.getValues();
        for (int y = 0; y < val.length; y++) {
            for (int x = 0; x < val.length; x++) {
                if (val[x][y]) sum++;
            }
        }
        sum -= (long) countMonsters(t) * monsterLen;
        return sum;
    }

    public static boolean containsMonster(int x, int y, Tile t) {
        try {
            boolean[][] val = t.getValues();
            for (Pos p : monster()) {
                if (!val[x + p.x][y + p.y]) return false;
            }
            return true;
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
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

    public static Tile findMatchingTile(Tile west, Tile north, List<Tile> all) {
        List<Tile> matching = new ArrayList<>(4);
        for (Tile other : all) {
            //unmodified
            if ((west == null || west.matches(Direction.EAST, other))
                    && (north == null || north.matches(Direction.SOUT, other))) return other;
            //mirrored
            Tile mir = other.flipHor();
            if ((west == null || west.matches(Direction.EAST, mir))
                    && (north == null || north.matches(Direction.SOUT, mir))) return mir;
            //rotated 90°
            Tile rot = other.rotate90();
            if ((west == null || west.matches(Direction.EAST, rot))
                    && (north == null || north.matches(Direction.SOUT, rot))) return rot;
            //rotated 90° and mirrored
            mir = rot.flipHor();
            if ((west == null || west.matches(Direction.EAST, mir))
                    && (north == null || north.matches(Direction.SOUT, mir))) return mir;
            //rotated 180°
            rot = rot.rotate90();
            if ((west == null || west.matches(Direction.EAST, rot))
                    && (north == null || north.matches(Direction.SOUT, rot))) return rot;
            //rotated 180° and mirrored
            mir = rot.flipHor();
            if ((west == null || west.matches(Direction.EAST, mir))
                    && (north == null || north.matches(Direction.SOUT, mir))) return mir;
            //rotated 270°
            rot = rot.rotate90();
            if ((west == null || west.matches(Direction.EAST, rot))
                    && (north == null || north.matches(Direction.SOUT, rot))) return rot;
            //rotated 270° and mirrored
            mir = rot.flipHor();
            if ((west == null || west.matches(Direction.EAST, mir))
                    && (north == null || north.matches(Direction.SOUT, mir))) return mir;
            //no further checks needed as they are all covered by these 8
            //see: https://stackoverflow.com/a/23550668
        }
        return null;
    }

    public static Tile findNWCorner(List<Tile> tiles) {
        for (Tile tile : tiles) {
            boolean n = false, w = false;
            for (Tile other : tiles) {
                if (other == tile) continue;
                //unmodified
                if (tile.matches(Direction.NORTH, other)) n = true;
                if (tile.matches(Direction.WEST, other)) w = true;
                //mirrored
                Tile mir = other.flipHor();
                if (tile.matches(Direction.NORTH, mir)) n = true;
                if (tile.matches(Direction.WEST, mir)) w = true;
                //rotated 90°
                Tile rot = other.rotate90();
                if (tile.matches(Direction.NORTH, rot)) n = true;
                if (tile.matches(Direction.WEST, rot)) w = true;
                //rotated 90° and mirrored
                mir = rot.flipHor();
                if (tile.matches(Direction.NORTH, mir)) n = true;
                if (tile.matches(Direction.WEST, mir)) w = true;
                //rotated 180°
                rot = rot.rotate90();
                if (tile.matches(Direction.NORTH, rot)) n = true;
                if (tile.matches(Direction.WEST, rot)) w = true;
                //rotated 180° and mirrored
                mir = rot.flipHor();
                if (tile.matches(Direction.NORTH, mir)) n = true;
                if (tile.matches(Direction.WEST, mir)) w = true;
                //rotated 270°
                rot = rot.rotate90();
                if (tile.matches(Direction.NORTH, rot)) n = true;
                if (tile.matches(Direction.WEST, rot)) w = true;
                //rotated 270° and mirrored
                mir = rot.flipHor();
                if (tile.matches(Direction.NORTH, mir)) n = true;
                if (tile.matches(Direction.WEST, mir)) w = true;
            }
            if (!n && !w) return tile;
        }
        return null;
    }

    private static class Pos {
        public final int x;
        public final int y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
