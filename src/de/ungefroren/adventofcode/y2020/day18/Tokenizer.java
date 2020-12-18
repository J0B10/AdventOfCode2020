package de.ungefroren.adventofcode.y2020.day18;

import de.ungefroren.adventofcode.y2020.day18.tokens.Number;
import de.ungefroren.adventofcode.y2020.day18.tokens.Operation;
import de.ungefroren.adventofcode.y2020.day18.tokens.Parenthesis;
import de.ungefroren.adventofcode.y2020.day18.tokens.Token;

import java.text.DecimalFormat;

public abstract class Tokenizer {

    private static final char DECIMAL_SEPARATOR = ((DecimalFormat) DecimalFormat.getInstance())
            .getDecimalFormatSymbols().getDecimalSeparator();

    public static Token tokenize(String s) {
        return tokenize(null, null, s.replaceAll("\\s", ""));
    }

    private static Token tokenize(Token val1, Operator operator, String val2) {
        if (val2.isEmpty()) {
            if (operator != null && val1 != null) {
                return new Operation(new Number(operator.getNeutralElement()), operator, val1);
            } else {
                return null;
            }
        }

        //Parse next token in line (from right to left)
        Token nextInLine = null;
        int index = 0;
        char c = val2.charAt(index++);
        if (c == '(') { //tokenize parenthesis
            int depth = 1;
            for (; index < val2.length(); index++) {
                c = val2.charAt(index);
                if (c == '(') depth++;
                else if (c == ')') depth--;

                if (depth == 0) {
                    nextInLine = new Parenthesis( tokenize(null, null,val2.substring(1, index)));
                    break;
                }
            }
            if (nextInLine == null) throw new IllegalArgumentException("unbalanced parenthesis in token: " + val2);
        } else if (Character.isDigit(c)) { //tokenize numbers
            for (; index < val2.length(); index++) {
                c = val2.charAt(index);
                if (!Character.isDigit(c) && c != DECIMAL_SEPARATOR) {
                    break;
                }
            }
            nextInLine = new Number(Double.parseDouble(val2.substring(0, index--)));
        } else {
            throw new IllegalArgumentException("invalid token: " + val2);
        }

        if (index < val2.length() - 1) {
            try {
                Operator nextOperator = Operator.valueOf(val2.charAt(++index));
                String newVal = val2.substring(++index);

                //no token right of this token, parse next
                if (operator == null) {
                    return tokenize(nextInLine, nextOperator, newVal);
                }

                //next operation has higher priority, tokenize it first
                if (nextOperator.getPriority() > operator.getPriority()) {
                    return new Operation(val1, operator, tokenize(nextInLine, nextOperator, newVal));
                }

                //next operation has lower priority, tokenize this first
                return tokenize(new Operation(val1, operator, nextInLine), nextOperator, newVal);

            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("invalid token: " + val2, e);
            }
        } else {
            if (operator == null) {
                return nextInLine;
            } else {
                return new Operation(val1, operator, nextInLine);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(tokenize("2 * 3 + (4 * 5)").resolve());
        System.out.println(tokenize("5 + (8 * 3 + 9 + 3 * 4 * 3)").resolve());
        System.out.println(tokenize("5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))").resolve());
        Token t = tokenize("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2");
        System.out.println(t.resolve());
    }
}
