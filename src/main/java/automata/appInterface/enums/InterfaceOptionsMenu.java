package automata.appInterface.enums;

public enum InterfaceOptionsMenu {
    //MENU OPTIONS
    GET_NEXT_TOKEN_OPTION("Get next token", 0),
    GET_ALL_TOKEN_OPTION("Get all tokens", 1),
    EXIT_OPTION_MESSAGE("Exit", 2);

    // ...otros mensajes de error...

    private final String message;
    private final  Integer option;

    InterfaceOptionsMenu(String message, Integer option) {
        this.message = message;
        this.option = option;
    }

    @Override
    public String toString() {
        return message;
    }

    public Integer getOption() {return this.option;  }
    }
