package Parser.impl;

import Parser.Parser;
import Service.LoggerService;
import composite.TextHandler;
import composite.impl.Paragraph;
import composite.impl.Sentence;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParagraphParser extends Parser{


    private static ParagraphParser parser;

    public static ParagraphParser getParser(){
        if(parser == null){
            parser = new ParagraphParser();
        }
        return parser;
    }

    private final String SENTENCE_REGEX = "([!?.]|\\.{3})\\s+";
    private final String SYMBOL_REGEX = "([!?.]|\\.{3})(\\s|$)";



    private ParagraphParser(){
        LoggerService.LOGGER.info("Paragraph parser created");

    }

    @Override
    public boolean parse(TextHandler textHandler) {
        LoggerService.LOGGER.info("started parsing paragraphs");
        Pattern symbolPattern = Pattern.compile(SYMBOL_REGEX);
        for(Paragraph paragraph : textHandler.getParagraphs()) {
            String[] sentences = paragraph.getText().split(SENTENCE_REGEX);
                LoggerService.LOGGER.info("matching sentences with regex");
            Matcher symbolMatcher = symbolPattern.matcher((paragraph.getText()));
            for(String sentence : sentences) {
                symbolMatcher.find();
                paragraph.add(new Sentence(sentence, symbolMatcher.group().trim()));
            }
        }
        return checkNext(textHandler);
    }
}
