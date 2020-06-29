public class MorseConverter {

    /**
     * Encodes a single character to morse code
     *
     * @param x
     * @return morse code
     */
    static String morseEncode(char x) {
        x = Character.toLowerCase(x);
        return switch (x) {
            case 'a' -> ".-";
            case 'b' -> "-...";
            case 'c' -> "-.-.";
            case 'd' -> "-..";
            case 'e' -> ".";
            case 'f' -> "..-.";
            case 'g' -> "--.";
            case 'h' -> "....";
            case 'i' -> "..";
            case 'j' -> ".---";
            case 'k' -> "-.-";
            case 'l' -> ".-..";
            case 'm' -> "--";
            case 'n' -> "-.";
            case 'o' -> "---";
            case 'p' -> ".--.";
            case 'q' -> "--.-";
            case 'r' -> ".-.";
            case 's' -> "...";
            case 't' -> "-";
            case 'u' -> "..-";
            case 'v' -> "...-";
            case 'w' -> ".--";
            case 'x' -> "-..-";
            case 'y' -> "-.--";
            case 'z' -> "--..";
            case '0' -> "-----";
            case '1' -> ".----";
            case '2' -> "..---";
            case '3' -> "...--";
            case '4' -> "....-";
            case '5' -> ".....";
            case '6' -> "-....";
            case '7' -> "--...";
            case '8' -> "---..";
            case '9' -> "----.";
            case ' ' -> "/";

            default -> throw new IllegalStateException("Unexpected value: " + x);
        };
    }

    /**
     * Decodes morse to a single character
     *
     * @param morse
     * @return single character
     */
    public static String morseDecode(String morse) {

        return switch (morse) {
            case ".-" -> "a";
            case "-..." -> "b";
            case "-.-." -> "c";
            case "-.." -> "d";
            case "." -> "e";
            case "..-." -> "f";
            case "--." -> "g";
            case "...." -> "h";
            case ".." -> "i";
            case ".---" -> "j";
            case "-.-" -> "k";
            case ".-.." -> "l";
            case "--" -> "m";
            case "-." -> "n";
            case "---" -> "o";
            case ".--." -> "p";
            case "--.-" -> "q";
            case ".-." -> "r";
            case "..." -> "s";
            case "-" -> "t";
            case "..-" -> "u";
            case "...-" -> "v";
            case ".--" -> "w";
            case "-..-" -> "x";
            case "-.--" -> "y";
            case "--.." -> "z";
            case "-----" -> "0";
            case ".----" -> "1";
            case "..---" -> "2";
            case "...--" -> "3";
            case "....-" -> "4";
            case "....." -> "5";
            case "-...." -> "6";
            case "--..." -> "7";
            case "---.." -> "8";
            case "----." -> "9";
            case "/" -> " ";
            default -> throw new IllegalStateException("Unexpected value: " + morse);
        };

    }

    /**
     * translates words to morse
     *
     * @param word the word(s) to be translated
     * @return morse code for requested words
     */
    public static StringBuilder translateToMorse(String word) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < word.length(); i++) {
            result.append(morseEncode(word.charAt(i)));
            result.append(" ");
        }

        return result;
    }


    /**
     * translates morse to words
     * @param morse
     * @return word(s)
     */
    public static StringBuilder translateToWord(String morse){
        StringBuilder result = new StringBuilder();

        String[] s1 = morse.split(" ");

        for (String s : s1) {
            result.append(morseDecode(s));
        }
        return result;
    }

}
