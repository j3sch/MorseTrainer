import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class Translator {

    static final char[] letters = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
            'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1',
            '2', '3', '4', '5', '6', '7', '8', '9', '.', ',', '?', '!'};

    static final String[] morse = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..",
            ".---", "-.-", ".-..", "--", "-.", "---", ".---.", "--.-", ".-.",
            "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--..", "-----", ".----",
            "..---", "...--", "....-", ".....", "-....", "--...", "---..", "----.", ".-.-.-", "--..--", "..--..", "-.-.--"};

    /* -- single word to morsecode -- */
    public static String abcToMorse(String word) {

        StringBuilder result = new StringBuilder();
        int len = word.length();
        char[] lettersArray = word.toCharArray();

        for (int i = 0; i < len; i++) {                     //go through every letter of the word
            for (int j = 0; j < letters.length; j++) {      //goes through each element of the variable letters
                if (lettersArray[i] == letters[j]) {        //which position the letter has in the variable
                    result.append(morse[j]);                //copies same position from the morse variable to result
                }
            }
        }
        return result.toString();
    }

    /* -- sentences to morsecode -- */
    public static String abcToMorse(char[] input) {

        StringBuilder result = new StringBuilder();
        int len = input.length;

        for (int i = 0; i < len; i++) {                     //go through every letter of the sentences
            if (input[i] == ' ') {                          //new word in the sentence -> add whitespace to result
                result.append(" ");
            } else {
                for (int j = 0; j < letters.length; j++) {  //goes through each element of the variable letters
                    if (input[i] == letters[j]) {           //which position the letter has in the variable
                        result.append(morse[j]);            //copies the same position from the morse variable to result
                        break;
                    }
                    if (j == letters.length - 1) {          //no match with current letter -> Error
                        return "Error, input not valid!";

                    }
                }
            }
        }
        return result.toString();
    }

    /* -- single morsecode word to word -- */
    public String morseToAbc(String input) {

        StringBuilder result = new StringBuilder();
        String[] morseArray = input.split(" ");       //splits the single morsecode letters
        int len = morseArray.length;

        for (int i = 0; i < len; i++) {                     //go through every morsecode letter of the word
            for (int j = 0; j < morse.length; j++) {        //goes through each element of the variable morse
                if (morseArray[i].equals(morse[j])) {       //which position the morsecode letter has in the variable
                    result.append(letters[j]);              //copies the same position from the letters variable to result
                    break;
                }
                if (j == morse.length -1) {                 //no match with current morsecode letter -> Error
                    return "Error, input not valid!";

                }
            }
        }
        return result.toString();
    }

    /* -- morsecode sentences to sentences -- */
    public String morseToAbc(String[] input) {

        StringBuilder result = new StringBuilder();
        int len = input.length;

        for (int i = 0; i < len; i++) {                     //go through every morsecode letter of the sentence
            if (input[i].equals(" ")) {                     //new word in the sentence -> add whitespace to result
                result.append(" ");
            } else {
                for (int j = 0; j < morse.length; j++) {    //goes through each element of the variable morse
                    if (input[i].equals(morse[j])) {        //which position the morsecode letter has in the variable
                        result.append(letters[j]);          //copies the same position from the letters variable to result
                        break;
                    }
                    if (j == morse.length -1) {             //no match with current morsecode letter -> Error
                        return "Error, input not valid!";
                    }
                }
            }
        }
        return result.toString();
    }

    /**
     *
     * @param input String, the word you want to convert to morse code sound
     * @throws LineUnavailableException
     *
     * check the input string for alphabet or morse.
     * If alphabet was found translate the string to morse. pass dot or stroke by character to sound function
     */
    public static void morseToSound(String input) throws LineUnavailableException {
        if (!input.matches("^[\\. -]+$")) { // check if morse code is not found
            // todo: convert String to morse code
        }

        char[] code = input.toCharArray();
        for (char c : code) {
            if (c == ' ') {
                try {
                    Thread.sleep(800); // delay to split up chars
                } catch (InterruptedException e) {}
            } else {
                generateSound(c);
            }
        }
    }

    /**
     *
     * @param c char, '.' or '-', will be converted to sound
     * @throws LineUnavailableException
     *
     * use the transfer parameter to generate a tone that is either long or short
     */
    public static void generateSound(char c) throws LineUnavailableException {
        int duration; // ms
        int vol = 1;
        float sampleRate = 8000f;
        byte[] buf = new byte[1];

        AudioFormat af = new AudioFormat(
                sampleRate,         // sampling rate per sec
                8,     // memory size for one sample value
                1,          // mono
                true,        // 8bit with sign (+/-)
                false);
        SourceDataLine sdl = AudioSystem.getSourceDataLine(af);

        if (c == '-') {
            duration = 400;
        } else {
            duration = 100;
        }

        sdl.open(af);
        sdl.start();

        // generate a sinus function
        for (int i = 0; i < duration * 8; i++) {
            double angle = i / (sampleRate / 440) * 2.0 * Math.PI;
            buf[0] = (byte) (Math.sin(angle) * 127.0 * vol); // current y-value of sinus

            // generate a sound from af that the user can hear
            sdl.write(buf, 0, 1);
        }
        sdl.drain();
        sdl.stop();
        sdl.close();
    }

}