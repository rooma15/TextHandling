package Parser.impl;

import Parser.Parser;
import Service.LoggerService;
import composite.TextHandler;
import composite.impl.Paragraph;
import exception.EmptyTextException;

import java.util.Arrays;
import java.util.Collection;

public class TextParser extends Parser{


    private static TextParser parser;

    public static TextParser getParser(){
        if(parser == null){
            parser = new TextParser();
        }
        return parser;
    }

    private final String PARAGRAPH_REGEX = "\\t|\\s{4}";


    private TextParser(){
        LoggerService.LOGGER.info("Text Parser created");
    }

    @Override
    public boolean parse(TextHandler textHandler){
        if(textHandler.getText().equals("")){
            throw new EmptyTextException("Text can not be empty");
        }
        LoggerService.LOGGER.info("parsing started");
        Collection<String> indents = Arrays.asList(textHandler.getText().split(PARAGRAPH_REGEX));
        for(String indent : indents) {
            textHandler.getParagraphs().add(new Paragraph(indent));
        }
        return checkNext(textHandler);
    }
}