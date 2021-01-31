package Parser;

import composite.Lexeme;
import composite.TextHandler;
import composite.impl.Expression;
import composite.impl.Word;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class SentenceParserTest {
    @Test
    public void lexemeDeterminationTest(){
        String text = "Hello 123 friend.";
        TextHandler textHandler = new TextHandler(text);
        textHandler.disassemble();
        List<Lexeme> lexemes = textHandler.getParagraphs().get(0).get(0).getLexemes();
        assertEquals(Word.class, lexemes.get(0).getLexemeType());
        assertEquals(Expression.class, lexemes.get(1).getLexemeType());
        assertEquals(Word.class, lexemes.get(2).getLexemeType());
    }


    @Test
    public void bigAmountOfWhitespacesTest(){
        String text = "Hello   123  friend.";
        TextHandler textHandler = new TextHandler(text);
        textHandler.disassemble();
        List<Lexeme> lexemes = textHandler.getParagraphs().get(0).get(0).getLexemes();
        assertEquals("Hello", lexemes.get(0).getText());
        assertEquals("123", lexemes.get(1).getText());
        assertEquals("friend", lexemes.get(2).getText());
    }

}
