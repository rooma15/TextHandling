package Service;

import org.junit.Test;


import static org.junit.Assert.*;

public class InterpreterTest {

    @Test
    public void translationToRPNTest(){

        String[] inputTokens = "( 8 + 2 * 5 ) / ( 1 + 3 * 2 - 4 )".split(" ");
        assertEquals("825*+132*+4-/", String.join("", Interpreter.infixToRPN(inputTokens)));
    }

    @Test
    public void calculationAccuracyTest(){
        String[] inputTokens = "( 8 + 2 * 5 ) / ( 1 + 3 * 2 - 4 )".split(" ");
        String[] outputTokens = Interpreter.infixToRPN(inputTokens);
        Interpreter.Expr expr = Interpreter.parse(String.join(" ", outputTokens));
        assertEquals((8+2*5)/(1+3*2-4), expr.interpret());
    }
}
