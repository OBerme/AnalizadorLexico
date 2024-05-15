package automata.appInterface;

import automata.AnalizadorLexico;
import automata.AutomataFinito;
import automata.Token;
import automata.appInterface.enums.InterfaceOptionsMenu;

import java.util.*;

import static automata.appInterface.enums.InterfaceMessages.*;
import static automata.appInterface.enums.InterfaceOptionsMenu.*;
import static automata.appInterface.enums.InterfaceStaticOptions.*;

public class AutomataInterfaceConsole {
    private AutomataFinito automata;
    private Scanner scn;

    private final HashMap<Integer, String> tokensIdentif;

    public AutomataInterfaceConsole(AutomataFinito automata,
                                    HashMap<Integer, String> tokensIdentif){
        this.automata = automata;
        this.tokensIdentif = tokensIdentif;
        this.start();
    }

    private void start(){
        this.scn = new Scanner(System.in);
        System.out.println(WELCOME_MESSAGE);

        int menuOption;
        while(true){
            showMenu();
            menuOption = getMenuOption(scn);
            if(menuOption== EXIT_OPTION_MESSAGE.getOption()){
                System.out.println(EXIT_MESSAGE_CONFIRMS_MESSAGE);
                if(confims(scn)) break;
            }
            else
                doMenuOption(menuOption,scn);
        }

        System.out.println(BYE_MESSAGE);
        this.scn.close();
    }

    private void doMenuOption(int menuOption, Scanner scn) {
        if(isOption(menuOption)){
            if(menuOption == GET_NEXT_TOKEN_OPTION.getOption()){

                String nextString = getNextString(scn);
                try {
                    AnalizadorLexico ana = new AnalizadorLexico(this.automata, nextString);
                    putTokensIdentif(ana);
                    List<Token> tokens = getTokens(ana);
                    if(!tokens.isEmpty()){
                        System.out.println(GETTED_TOKENS_MESSAGE);
                        for(Token token : tokens){
                            System.out.println(token);
                        }
                    }
                    else
                        System.out.println(DIDNT_FOUND_TOKENS);


                } catch (Exception e) {
                    System.out.println(SOMETHING_WENT_WRONG + ":");
                    System.out.println(e.getMessage()+"\n");
                }
            }
        }
        else
            System.out.println(NOT_AN_OPTION);

    }

    private List<Token> getTokens(AnalizadorLexico ana) {
        List<Token> tokenList = new ArrayList<>();
        while(ana.hasMoreTokens()){
            tokenList.add(ana.nextToken());
        }
        return tokenList;
    }

    private void putTokensIdentif(AnalizadorLexico ana) {
        for(Integer nEstado : this.tokensIdentif.keySet()){
            ana.addTokenIdentificativo(nEstado, this.tokensIdentif.get(nEstado));
        }
    }


    private static String getNextString(Scanner scanner) {
        System.out.println(GET_NEXT_STRING);
        String input = scanner.next().trim();
        // Verificar si la entrada no está vacía y no contiene solo espacios
        if (input.isEmpty()) {
            System.out.println(EMPTY_OPERATION);
            return getNextString(scanner);
        }

        return input;
    }

    private static boolean confims(Scanner scn) {
        try{
            String choose = scn.next().toLowerCase().trim();

            if(choose.equals(YES_CONFIRMS.toString())
                    || choose.equals(NO_CONFIRMS.toString()))
                return choose.equals("yes");
            System.out.println("Please give me a valid option");
        }
        catch(Exception e){
            System.out.println(WRONG_CONFIRMS_FORMAT);
        }
        return confims(scn);
    }

    private static int getMenuOption(Scanner scn) {
        int num = getNumber(scn);;
        if(!isOption(num)){
            System.out.println(VALID_OPTION);
            showMenu();
            return getMenuOption(scn);
        }
        return num;
    }

    private static boolean isOption(int num) {
        return  GET_NEXT_TOKEN_OPTION.getOption() == num
                || EXIT_OPTION_MESSAGE.getOption() == num
                || GET_ALL_TOKEN_OPTION.getOption() == num;
    }


    private static void showMenu() {
        if(InterfaceOptionsMenu.values().length > 0){
            System.out.println(PLEASE_GIVE_AN_OPTION);
            for (InterfaceOptionsMenu option : InterfaceOptionsMenu.values()) {
                System.out.println("Option: " + option.getOption() + " - " + option);
            }
        }
        else{
            System.out.println(NO_OPTIONS);
        }
    }

    private static int getNumber(Scanner scn){
        try{
            int num = scn.nextInt();
            return num;
        }
        catch(Exception e){
            scn.nextLine();
            System.out.println(WRONG_INTEGER_FORMAT);
            return getNumber(scn);
        }
    }
}
