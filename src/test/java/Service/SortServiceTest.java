package Service;

import composite.TextHandler;
import org.junit.Test;


import static org.junit.Assert.*;

public class SortServiceTest {

    @Test
    public void sortAccuracyTest(){
        String text = "Hello my friend. I am Roman.    How are you? I am okay! And you?    Nice test, awesome code!";
        TextHandler textHandler = new TextHandler(text);
        textHandler.disassemble();
        SortService service = SortService.getService();
        service.paragraphSort(textHandler);
        assertEquals("Nice test, awesome code!\n\tHello my friend. I am Roman.\n\tHow are you? I am okay! And you?\n\t", textHandler.assemble());
    }

    @Test(expected = IllegalStateException.class)
    public void textHandlerIsNotInitialised() throws IllegalStateException{
        String text = "text";
        TextHandler textHandler = new TextHandler(text);
        SortService service = SortService.getService();
        service.paragraphSort(textHandler);
    }
}
