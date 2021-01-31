package Service;

import composite.TextHandler;
import composite.impl.Paragraph;

import java.util.Comparator;
import java.util.List;

public class SortService {
    private static SortService service;

    public static SortService getService(){
        if(service == null){
            service = new SortService();
        }
        return service;
    }

    public void paragraphSort(TextHandler textHandler){
        if(!textHandler.isDisassembled()){
            throw new IllegalStateException("Text is not disassembled. Call method disassemble before sorting");
        }
        LoggerService.LOGGER.info("Start sorting paragraphs by amount of sentences");
        List<Paragraph> paragraphs = textHandler.getParagraphs();
        paragraphs.sort(new Comparator<Paragraph>() {
            @Override
            public int compare(Paragraph obj1, Paragraph obj2) {
                return Integer.compare(obj1.getAmountOfSentences(), obj2.getAmountOfSentences());
            }
        });
    }
}
