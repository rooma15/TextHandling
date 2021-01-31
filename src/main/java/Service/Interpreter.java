package Service;

import java.util.*;

public class Interpreter {
    private static final int LEFT_ASSOC = 0;
    private static final int RIGHT_ASSOC = 1;


    private static final Map<String, int[]> OPERATORS = new HashMap<String, int[]>();

    static {

        OPERATORS.put("+", new int[]{9, LEFT_ASSOC});
        OPERATORS.put("-", new int[]{9, LEFT_ASSOC});
        OPERATORS.put("*", new int[]{10, LEFT_ASSOC});
        OPERATORS.put("/", new int[]{10, LEFT_ASSOC});
        OPERATORS.put("|", new int[]{5, LEFT_ASSOC});
        OPERATORS.put("&", new int[]{7, LEFT_ASSOC});
        OPERATORS.put("^", new int[]{6, LEFT_ASSOC});
        OPERATORS.put("<<", new int[]{8, LEFT_ASSOC});
        OPERATORS.put(">>", new int[]{8, LEFT_ASSOC});
        OPERATORS.put("~", new int[]{11, RIGHT_ASSOC});
    }


    private static boolean isOperator(String token) {
        return OPERATORS.containsKey(token);
    }


    private static boolean isAssociative(String token, int type) {
        if(!isOperator(token)) {
            throw new IllegalArgumentException("Invalid token: " + token);
        }

        if(OPERATORS.get(token)[1] == type) {
            return true;
        }
        return false;
    }


    private static final int cmpPrecedence(String token1, String token2) {
        if(!isOperator(token1) || !isOperator(token2)) {
            throw new IllegalArgumentException("Invalid tokens: " + token1
                    + " " + token2);
        }
        return OPERATORS.get(token1)[0] - OPERATORS.get(token2)[0];
    }


    public static String[] infixToRPN(String[] inputTokens) {
        LoggerService.LOGGER.info("Translating to Reverse polish notation");
        ArrayList<String> out = new ArrayList<String>();
        Stack<String> stack = new Stack<String>();


        for(String token : inputTokens) {

            if(isOperator(token)) {

                while(!stack.empty() && isOperator(stack.peek())) {
                    if((isAssociative(token, LEFT_ASSOC) &&
                            cmpPrecedence(token, stack.peek()) <= 0) ||
                            (isAssociative(token, RIGHT_ASSOC) &&
                                    cmpPrecedence(token, stack.peek()) < 0)) {
                        out.add(stack.pop());
                        continue;
                    }
                    break;
                }

                stack.push(token);
            } else if(token.equals("(")) {
                stack.push(token);  //
            } else if(token.equals(")")) {
                while(!stack.empty() && !stack.peek().equals("(")) {
                    out.add(stack.pop());
                }
                stack.pop();
            } else {
                out.add(token);
            }
        }
        while(!stack.empty()) {
            out.add(stack.pop());
        }
        String[] output = new String[out.size()];
        return out.toArray(output);
    }


    @FunctionalInterface
    public interface Expr {
        int interpret();


        static Expr plus(Expr left, Expr right) {
            return () -> left.interpret() + right.interpret();
        }

        static Expr minus(Expr left, Expr right) {
            return () -> left.interpret() - right.interpret();
        }

        static Expr multiply(Expr left, Expr right) {
            return () -> left.interpret() * right.interpret();
        }

        static Expr divide(Expr left, Expr right) {
            return () -> left.interpret() / right.interpret();
        }

        static Expr disjunction(Expr left, Expr right) {
            return () -> left.interpret() | right.interpret();
        }

        static Expr conjunction(Expr left, Expr right) {
            return () -> left.interpret() & right.interpret();
        }


        static Expr XOR(Expr left, Expr right) {
            return () -> left.interpret() ^ right.interpret();
        }


        static Expr rightShift(Expr left, Expr right) {
            return () -> left.interpret() >> right.interpret();
        }


        static Expr leftShift(Expr left, Expr right) {
            return () -> left.interpret() << right.interpret();
        }


        static Expr addon(Expr left) {
            return () -> ~left.interpret();
        }


        static Expr variable(String name) {
            return () -> Integer.parseInt(name);
        }
    }

    private static Expr parseToken(String token, ArrayDeque<Expr> stack) {
        Expr left, right;
        switch (token) {
            case "+":
                right = stack.pop();
                left = stack.pop();
                return Expr.plus(left, right);
            case "-":
                right = stack.pop();
                left = stack.pop();
                return Expr.minus(left, right);
            case "*":
                right = stack.pop();
                left = stack.pop();
                return Expr.multiply(left, right);
            case "/":
                right = stack.pop();
                left = stack.pop();
                return Expr.divide(left, right);
            case "&":
                right = stack.pop();
                left = stack.pop();
                return Expr.conjunction(left, right);
            case "|":
                right = stack.pop();
                left = stack.pop();
                return Expr.disjunction(left, right);
            case "^":
                right = stack.pop();
                left = stack.pop();
                return Expr.XOR(left, right);
            case "<<":
                right = stack.pop();
                left = stack.pop();
                return Expr.leftShift(left, right);
            case ">>":
                right = stack.pop();
                left = stack.pop();
                return Expr.rightShift(left, right);
            case "~":
                left = stack.pop();
                return Expr.addon(left);
            default:
                return Expr.variable(token);
        }
    }

    public static Expr parse(String expression) {
        ArrayDeque<Expr> stack = new ArrayDeque<Expr>();
        for(String token : expression.split(" ")) {
            stack.push(parseToken(token, stack));
        }
        return stack.pop();
    }

}
