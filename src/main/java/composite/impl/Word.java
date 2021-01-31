package composite.impl;

import composite.BaseLexeme;

public class Word implements BaseLexeme, Comparable<String>{
    private final String word;

    public Word(String word){
        this.word = word;
    }


    public String get(){
        return word;
    }


    @Override
    public int compareTo(String s) {
        return word.compareTo(s);
    }

    @Override
    public String toString() {
        return word;
    }
}
