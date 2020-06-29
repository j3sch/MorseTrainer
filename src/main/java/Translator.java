

public class Translator {
    public static String main(word) {

        String[] letters = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n",
                            "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1",
                            "2", "3", "4", "5", "6", "7", "8", "9"};

        String[] morse = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..",
                          ".---", "-.-", ".-..", "--", "-.", "---", ".---.", "--.-", ".-.",
                          "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--..", "-----",
                          ".----", "..---", "...--", "....-", ".....", "-....", "--...", "---..",
                          "----."};

        int len = word.length();
        String result = "";

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < letters.length; j++) {
                if (word.substring(i, i+1).equals(letters[j])) {
                    result += morse[j];
                    break;
                }
            }
        }
        return result;
    }
}
