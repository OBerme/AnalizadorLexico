package automata;
public class Token {
    private String idToken;
    private String lexema;

    public Token(String idToken, String lexema) {
        this.idToken = idToken;
        this.lexema = lexema;
    }

    public String getIdToken() {
        return idToken;
    }

    public String getLexema() {
        return lexema;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Token){
            Token acToken = (Token) obj;
            return acToken.getIdToken().equals(this.getIdToken()) && acToken.getLexema().equals(this.getLexema());
        }
        return false;
    }
}
