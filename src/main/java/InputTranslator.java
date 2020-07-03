import java.util.Scanner;

public class InputTranslator {

    Scanner sc = new Scanner(System.in);
    String input = sc.nextLine().toLowerCase();
    int len = input.length();

    public String lettersAndNumbers(String input) {

        char[] inputArray = input.toCharArray();
        boolean inputValid = true;

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < Translator.letters.length; j++) {
                if (inputArray[j] == Translator.letters[j]) {
                    break;
                }
                if (j == Translator.letters.length -1) {
                    inputValid = false;
                }
            }
        }
        if (inputValid) {
            return Translator.abcToMorse(input);
        } else {
            return "Error, input not valid!";
        }
    }

    public String Morsecode(String input) {

        String[] inputSplit = input.split(" ");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < inputSplit.length; i++) {
            for (int j = 0; j < Translator.morse.length; j++) {
                if (inputSplit[0].equals(Translator.morse[j])) {
                    result.append(Translator.letters[j]);
                }
            }
        }
        return result.toString();
    }
}
