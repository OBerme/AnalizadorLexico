package automata.appInterface.enums;

public enum InterfaceStaticOptions {
    YES_CONFIRMS("yes"),
    NO_CONFIRMS("no");



    private final String message;

    InterfaceStaticOptions(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}

