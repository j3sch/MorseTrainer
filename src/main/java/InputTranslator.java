import java.util.Scanner;

public class InputTranslator {

    Scanner sc = new Scanner(System.in);
    String input = sc.nextLine().toLowerCase();
    int len = input.length();

    static char[] letters = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
            'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1',
            '2', '3', '4', '5', '6', '7', '8', '9'};

    static String[] morse = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..",
            ".---", "-.-", ".-..", "--", "-.", "---", ".---.", "--.-", ".-.",
            "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--..", "-----", ".----",
            "..---", "...--", "....-", ".....", "-....", "--...", "---..", "----."};

    public void lettersAndNumbers(String input) {

        char[] inputArray = input.toCharArray();
        boolean inputValid = true;

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < letters.length; j++) {
                if (inputArray[j] == letters[j]) {
                    break;
                }
                if (j == letters.length -1) {
                    inputValid = false;
                }
            }
        }
        if (inputValid) {
            System.out.println(Translator.abcToMorse(input));
        } else {
            System.out.println("Error, input not valid!");
        }
    }

    public void Morsecode(String input) {

        String[] inputSplit = input.split(" ");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < inputSplit.length; i++) {
            for (int j = 0; j < morse.length; j++) {
                if (inputSplit[i].equals(morse[j])) {
                    result.append(letters[j]);
                    break;
                }
            }
        }
        System.out.println(result.toString());
    }
}
