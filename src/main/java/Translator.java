public class Translator {
    public static String abcToMorse(String word) {

        int len = word.length();
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < LettersAndMorse.letters.length; j++) {
                if (word.substring(i, i + 1).equals(LettersAndMorse.letters[j])) {
                    result.append(LettersAndMorse.morse[j]);
                    break;
                }
            }
        }
        return result.toString();
    }
}
