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
}
