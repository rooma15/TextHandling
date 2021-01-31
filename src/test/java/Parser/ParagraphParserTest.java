package Parser;

import Parser.impl.ParagraphParser;
import composite.TextHandler;
import composite.impl.Sentence;
import org.junit.Test;
import static org.junit.Assert.*;

public class ParagraphParserTest {

    @Test
    public void oneSentenceTest(){
        String text = "Bye.";
        TextHandler textHandler = new TextHandler(text);
        textHandler.disassemble();
        Sentence sentence = textHandler.getParagraphs().get(0).get(0);
        assertEquals("Bye.", sentence.getText());
    }

    @Test
    public void symbolTest(){
        String text = "Bye... Lox!";
        TextHandler textHandler = new TextHandler(text);
        textHandler.disassemble();
        Sentence sentence = textHandler.getParagraphs().get(0).get(0);
        assertEquals("...", sentence.getSymbol());
        assertEquals("!", textHandler.getParagraphs().get(0).get(1).getSymbol());
    }


    @Test
    public void bigAmountOfWhiteSpacesTest(){
        String text = "Hello.   How are you ?  ";
        TextHandler textHandler = new TextHandler(text);
        textHandler.disassemble();
        assertEquals("Hello", textHandler.getParagraphs().get(0).get(0).getText());
        assertEquals("How are you ", textHandler.getParagraphs().get(0).get(1).getText());
    }
}
