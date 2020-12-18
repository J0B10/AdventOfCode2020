package de.ungefroren.adventofcode.y2020.day18.tokens;

import de.ungefroren.adventofcode.y2020.day18.Operator;

public class Operation implements Token {
    private final Token val1;
    private final Operator operator;
    private final Token val2;

    public Operation(Token val1, Operator operator, Token val2) {
        this.val1 = val1;
        this.operator = operator;
        this.val2 = val2;
    }

    @Override
    public double resolve() {
        return operator.calculate(val1.resolve(), val2.resolve());
    }

    @Override
    public String toString() {
        return val1.toString() + operator.toString() + val2.toString();
    }
}
