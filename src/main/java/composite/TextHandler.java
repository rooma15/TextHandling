package composite;

import Parser.impl.SentenceParser;
import Parser.impl.TextParser;
import Parser.impl.ParagraphParser;
import Service.LoggerService;
import composite.impl.Paragraph;
import composite.impl.Sentence;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TextHandler{
    private final List<Paragraph> paragraphs = new ArrayList<>();

    private final String text;

    private boolean isDisassembled = false;

    public TextHandler(String text){
        this.text = text;
    }

    public List<Paragraph> getParagraphs() {
        return paragraphs;
    }

    public String getText(){
        return text;
    }

    public boolean isDisassembled() {
        return isDisassembled;
    }

    public TextHandler disassemble(){
        isDisassembled = true;
         LoggerService.LOGGER.info("Start disassembling text");
         TextParser parser = TextParser.getParser();
         parser.setNextParser(ParagraphParser.getParser())
                 .setNextParser(SentenceParser.getParser());

        parser.parse(this);
        return this;
    }

    public String assemble(){
        LoggerService.LOGGER.info("Start assembling text");
        StringBuilder fullText = new StringBuilder();
        for(Paragraph paragraph : paragraphs) {
            for(Sentence sentence : paragraph.getSentences()) {
                fullText.append(sentence.getLexemes()
                                .stream()
                                .map(Lexeme::getText)
                                .collect(Collectors.joining(" ")));
                fullText.append(sentence.getSymbol()).append(" ");
            }
            fullText.deleteCharAt(fullText.length() - 1);
            fullText.append("\n\t");
        }
        return fullText.toString();
    }
}
