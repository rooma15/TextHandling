package composite.impl;

import composite.BaseLexeme;

public class Expression implements BaseLexeme {
    private final String expression;
    public Expression(String expr){
        this.expression = expr;
    }

    public String get() {
        return expression;
    }

    @Override
    public String toString() {
        return expression;
    }
}
