package composite;


public class Lexeme{
    private final BaseLexeme lexeme;

    public Lexeme(BaseLexeme lexeme) {
        this.lexeme = lexeme;
    }

    public Class<? extends BaseLexeme> getLexemeType(){
        return lexeme.getClass();
    }


    public String getText() {
        return lexeme.get();
    }

    @Override
    public String toString() {
        return lexeme.get();
    }
}
