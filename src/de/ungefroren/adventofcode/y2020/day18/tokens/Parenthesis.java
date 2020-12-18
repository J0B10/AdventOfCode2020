package de.ungefroren.adventofcode.y2020.day18.tokens;

public class Parenthesis implements Token {

    private final Token inside;

    public Parenthesis(Token inside) {
        this.inside = inside;
    }

    @Override
    public double resolve() {
        return inside.resolve();
    }

    @Override
    public String toString() {
        return '(' + inside.toString() + ')';
    }
}
