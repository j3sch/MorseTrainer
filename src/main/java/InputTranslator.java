import java.util.Scanner;

public class InputTranslator {

    Scanner sc = new Scanner(System.in);
    String input = sc.nextLine().toLowerCase();
    int len = input.length();

    public String lettersAndNumbers(String input) {

        char[] inputArray = input.toCharArray();

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < Translator.letters.length; j++) {
                if (inputArray[j] == Translator.letters[j]) {
                    break;
                }
                if (j == Translator.letters.length -1) {
                    return "Error, input not valid!";
                }
            }
        }
        return Translator.abcToMorse(input);
    }


    public String Morsecode(String[] input) {

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < len; i++) {
            if (input[i].equals(' ')) {
                result.append(' ');
            } else {
                for (int j = 0; j < Translator.morse.length; j++) {
                    if (input[i].equals(Translator.morse[j])) {
                        result.append(Translator.letters[j]);
                        break;
                    }
                    if (j == Translator.morse.length -1) {
                        return "Error, input not valid!";
                    }
                }
            }
        }
        return result.toString();
    }
}

