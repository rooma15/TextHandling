package Parser;
import composite.TextHandler;
import composite.impl.Paragraph;
import exception.EmptyTextException;
import org.junit.*;
import static org.junit.Assert.*;

public class TextParserTest {

    @Test
    public void oneParagraphTest(){
        String text = "Bye.";
        TextHandler textHandler = new TextHandler(text);
        textHandler.disassemble();
        Paragraph paragraph = textHandler.getParagraphs().get(0);
        assertEquals(text, paragraph.getText());
    }

    @Test(expected = EmptyTextException.class)
    public void emptyTextTest() throws EmptyTextException {
        String text = "";
        TextHandler textHandler = new TextHandler(text);
        textHandler.disassemble();
    }
}
