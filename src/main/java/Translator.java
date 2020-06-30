public class Translator {
    public static String abcToMorse(String word) {

        int len = word.length();
        String result = "";

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < LettersAndMorse.letters.length; j++) {
                if (word.substring(i, i + 1).equals(LettersAndMorse.letters[j])) {
                    result += LettersAndMorse.morse[j];
                    break;
                }
            }
        }
        return result;
    }
}
