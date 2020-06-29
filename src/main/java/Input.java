import java.util.Scanner;
package Translator;

public class Input {
    public static String main() {

        Scanner sc = new Scanner(System.in);
        String  input = sc.nextLine().toLowerCase();

        boolean morsecode = false;
        boolean morseAndLettes = false;
        int len = input.length();

        if (len == 0) {
            return "No, input!";
        }

        for (int i = 0; i < len; i++) {
            if (input.substring(i, i + 1).equals(".") || input.substring(i, i + 1).equals("-")) {           //Checkt, ob input Morsecode ist
                morsecode = true;
            } else {
                for (int j = 0; j < Translator.letters.length; j++) {
                    if (input.substring(i, i + 1).equals(Translator.letters[j])) {                          //Checkt, ob input Letters oder Numbers sind
                        morseAndLettes = true;
                        break;
                    } else {
                        return "Error, invalid expression!";                                                //Checkt, ob andere Zeichen eingegeben werden
                    }
                }

            }
            if (morsecode && morseAndLettes) {
                return "Error, only morse or only letters and numbers!";

            }
        }
        return input;
    }
}
