package de.ungefroren.adventofcode.y2020.day11;

public abstract class Sightline {

    protected final Sitable[][] seats;
    protected int x;
    protected int y;

    protected Sightline(int originX, int originY, Sitable[][] seats) {
        this.seats = seats;
        this.x = originX;
        this.y = originY;
    }

    protected abstract void next();

    public int inSight() {
        while (true) {
            next();
            if (x < 0 || x >= seats[0].length) return 0;
            if (y < 0 || y >= seats.length) return 0;
            if (seats[y][x].isOccupied()) return 1;
            else if (seats[y][x] instanceof Seat) return 0;
        }
    }


    public static class NORTH extends Sightline {

        protected NORTH(int originX, int originY, Sitable[][] seats) {
            super(originX, originY, seats);
        }

        @Override
        protected void next() {
            y--;
        }
    }
    public static class NORTHEAST extends Sightline {

        protected NORTHEAST(int originX, int originY, Sitable[][] seats) {
            super(originX, originY, seats);
        }

        @Override
        protected void next() {
            y--;
            x++;
        }
    }
    public static class EAST extends Sightline {

        protected EAST(int originX, int originY, Sitable[][] seats) {
            super(originX, originY, seats);
        }

        @Override
        protected void next() {
            x++;
        }
    }
    public static class SOUTHEAST extends Sightline {

        protected SOUTHEAST(int originX, int originY, Sitable[][] seats) {
            super(originX, originY, seats);
        }

        @Override
        protected void next() {
            y++;
            x++;
        }
    }
    public static class SOUTH extends Sightline {

        protected SOUTH(int originX, int originY, Sitable[][] seats) {
            super(originX, originY, seats);
        }

        @Override
        protected void next() {
            y++;
        }
    }
    public static class SOUTHWEST extends Sightline {

        protected SOUTHWEST(int originX, int originY, Sitable[][] seats) {
            super(originX, originY, seats);
        }

        @Override
        protected void next() {
            y++;
            x--;
        }
    }
    public static class WEST extends Sightline {

        protected WEST(int originX, int originY, Sitable[][] seats) {
            super(originX, originY, seats);
        }

        @Override
        protected void next() {
            x--;
        }
    }
    public static class NORTHWEST extends Sightline {

        protected NORTHWEST(int originX, int originY, Sitable[][] seats) {
            super(originX, originY, seats);
        }

        @Override
        protected void next() {
            x--;
            y--;
        }
    }
}
