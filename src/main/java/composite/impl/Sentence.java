package composite.impl;

import composite.BaseCompositeItem;
import composite.Lexeme;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Sentence implements BaseCompositeItem<Lexeme>, Iterable<Lexeme>{
    private final List<Lexeme> lexemes = new ArrayList<>();

    private final String symbol;

    private final String text;

    @Override
    public String getText() {
        return text;
    }

    public String getSymbol() {
        return symbol;
    }

    public Sentence(String text, String symbol){
        this.text = text;
        this.symbol = symbol;
    }

    public void add(Lexeme lexeme){
        lexemes.add(lexeme);
    }

    public Lexeme get(int index){
        return lexemes.get(index);
    }

    public List<Lexeme> getLexemes(){
        return lexemes;
    }

    public int getAmountOfLexemes(){
        return lexemes.size();
    }

    @Override
    public Iterator<Lexeme> iterator() {
        return lexemes.iterator();
    }
}
