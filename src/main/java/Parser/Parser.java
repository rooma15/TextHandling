package Parser;

import composite.TextHandler;

public abstract class Parser {
    Parser next;

    public Parser setNextParser(Parser next){
        this.next = next;
        return next;
    }

    public abstract boolean parse(TextHandler textHandler);

    protected boolean checkNext(TextHandler textHandler){
        if(this.next == null){
            return true;
        }
        return next.parse(textHandler);
    }

}
