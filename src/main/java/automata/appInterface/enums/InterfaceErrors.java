package automata.appInterface.enums;

public enum InterfaceErrors {
    INVALID_EXPRESSION("La expresión asignada no vale, pruebe con otra de nuevo"),
    DIVISION_BY_ZERO("No se puede dividir por cero");

    // ...otros mensajes de error...

    private final String message;

    InterfaceErrors(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
    }
