public class Translator {
    public static String abcToMorse(word) {

        int len = word.length();
        String result = "";

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < lettersAndMorse.letters.length; j++) {
                if (word.substring(i, i+1).equals(lettersAndMorse.letters[j])) {
                    result += lettersAndMorse.morse[j];
                    break;
                }
            }
        }
        return result;
    }
}
