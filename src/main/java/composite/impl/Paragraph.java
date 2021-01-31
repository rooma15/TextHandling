package composite.impl;

import composite.BaseCompositeItem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



public class Paragraph implements BaseCompositeItem<Sentence>, Iterable<Sentence>{
    private final List<Sentence> sentences = new ArrayList<>();

    private final String text;

    public Paragraph(String text){
        this.text = text;
    }

    public void add(Sentence sentence){
        sentences.add(sentence);
    }

    public Sentence get(int index){
        return sentences.get(index);
    }

    public List<Sentence> getSentences(){
        return sentences;
    }

    public int getAmountOfSentences(){
        return sentences.size();
    }

    @Override
    public Iterator<Sentence> iterator() {
        return sentences.iterator();
    }

    public String getText(){
        return text;
    }

}
