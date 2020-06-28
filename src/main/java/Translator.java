import java.util.Scanner;

public class Translator {
    public static void main(String[] args) {

        String[] letters = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n",
                            "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1",
                            "2", "3", "4", "5", "6", "7", "8", "9"};

        String[] morse = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..",
                          ".---", "-.-", ".-..", "--", "-.", "---", ".---.", "--.-", ".-.",
                          "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--..", "-----",
                          ".----", "..---", "...--", "....-", ".....", "-....", "--...", "---..",
                          "----."};

        Scanner sc = new Scanner(System.in);
        String  input = sc.nextLine().toLowerCase();
        int len = input.length();
        String result = "";
        boolean morsecode = false;
        boolean morseAndLettes = false;

        if (len == 0) {
            System.out.println("No Input");
        }

        for (int i = 0; i < len; i++) {
            if (input.substring(i, i+1).equals(".") || input.substring(i, i+1).equals("-")) {           //Checkt, ob input Morse oder Letters sind
                morsecode = true;
            } else {
                morseAndLettes = true;
            }
            if (morsecode && morseAndLettes) {
                System.out.println("Error, only morse or only letters and numbers!");
                break;
            }
            if (!morsecode) {                                                                         //Word -> Morsecode
                for (int j = 0; j < letters.length; j++) {
                    if (input.substring(i, i+1).equals(letters[j])) {
                        result += morse[j];
                        break;
                    }
                    if (j == letters.length - 1) {
                        System.out.println("Error, only morse or only letters and numbers!");
                    }
                }
            }
            /* if (morsecode) {
                for (int j = 0; j < morse.length; j++) {
                    if (input.substring(i, i+1).equals(morse[j])) {
                        result += letters[j];
                        break;
                    }
                }
            }
             */
        }
        System.out.println(result);
    }

}
