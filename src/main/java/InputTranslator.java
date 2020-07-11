
public class InputTranslator {

    public String lettersAndNumbers(String input) {

        StringBuilder result = new StringBuilder();
        int len = input.length();
        char[] wordArray = input.toCharArray();

        for (int i = 0; i < len; i++) {
            if (wordArray[i] == ' ') {
                result.append(' ');
            } else {
            for (int j = 0; j < Translator.letters.length; j++) {
                if (wordArray[i] == Translator.letters[j]) {
                    result.append(Translator.morse[j]);
                    break;
                }
                if (j == Translator.letters.length - 1) {
                    return "Error, input not valid!";
                }
            }
            }
        }
        return result.toString();
    }


    public String Morsecode(String[] input) {

        StringBuilder result = new StringBuilder();
        int len = input.length;

        for (int i = 0; i < len; i++) {
            if (input[i].equals(" ")) {
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

