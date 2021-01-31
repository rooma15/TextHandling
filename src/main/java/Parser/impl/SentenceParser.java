package Parser.impl;

import Parser.Parser;
import Service.Interpreter;
import Service.LoggerService;
import composite.Lexeme;
import composite.TextHandler;
import composite.impl.Expression;
import composite.impl.Paragraph;
import composite.impl.Sentence;
import composite.impl.Word;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class SentenceParser extends Parser{

    private static SentenceParser parser;

    static public SentenceParser getParser(){
        if(parser == null){
            parser = new SentenceParser();
        }
        return parser;
    }

    private final String SENTENCE_REGEX = "\\s+|([!?.]|\\.{3})";
    private final String EXPRESSION_REGEX = "^.*\\d.*$";

    private final String EXPRESSION_POSTPROC_REGEX = "(\\d+)|[|\\-*+&^/~()]|(<{2})|(>{2})";


    private SentenceParser(){
        LoggerService.LOGGER.info("Sentence parser created");
    }

    @Override
    public boolean parse(TextHandler textHandler) {
        LoggerService.LOGGER.info("starting parsing sentences");
        for(Paragraph paragraph : textHandler.getParagraphs()) {
            List<Sentence> sentences = paragraph.getSentences();
            for(Sentence sentence : sentences) {
                Collection<String> lexemes = Arrays.asList(sentence.getText().split(SENTENCE_REGEX));
                LoggerService.LOGGER.info("Determinating lexemes type");
                for(String lexeme : lexemes) {
                    lexemeTypeDetermination(lexeme, sentence);
                }
            }
        }
        return checkNext(textHandler);
    }


    private void lexemeTypeDetermination(String lexeme, Sentence sentence){
        Pattern pattern = Pattern.compile(EXPRESSION_REGEX);
        Matcher matcher = pattern.matcher(lexeme);
        if(matcher.matches()){
            String[] inputExp = expProcessing(lexeme);
            String[] outputExp = Interpreter.infixToRPN(inputExp);
            Interpreter.Expr expr = Interpreter.parse(String.join(" ", outputExp));
            int result = expr.interpret();
            sentence.add(new Lexeme(new Expression(Integer.toString(result))));
        }else{
            sentence.add(new Lexeme(new Word(lexeme)));
        }
    }

    private String[] expProcessing(String lexeme){
        Pattern expPattern = Pattern.compile(EXPRESSION_POSTPROC_REGEX);
        Matcher expMatcher = expPattern.matcher(lexeme);
        ArrayList<String> exp = new ArrayList<>();
        while(expMatcher.find()){
            exp.add(expMatcher.group());
        }
        return exp.toArray(new String[0]);
    }
}
