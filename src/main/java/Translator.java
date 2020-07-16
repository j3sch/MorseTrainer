import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import java.util.LinkedList;

public class Translator {

    static final char[] letters = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
            'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1',
            '2', '3', '4', '5', '6', '7', '8', '9', '.', ',', '?', '!'};

    static final String[] morse = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..",
            ".---", "-.-", ".-..", "--", "-.", "---", ".---.", "--.-", ".-.",
            "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--..", "-----", ".----",
            "..---", "...--", "....-", ".....", "-....", "--...", "---..", "----.", ".-.-.-", "--..--", "..--..", "-.-.--"};

    /**
     * single word to morsecode
     *
     * @param word
     * @return
     */
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

    /**
     * sentences to morsecode
     *
     * @param input
     * @return
     */
    public static String abcToMorse(char[] input){

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
                        result.append("?");

                    }
                }
            }
        }
        return result.toString();
    }

    /**
     * single morsecode word to word
     *
     * @param input
     * @return
     */
    public static String morseToAbc(String input) {

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
                    result.append("?");

                }
            }
        }
        return result.toString();
    }

    /**
     * morsecode sentences to sentencces
     *
     * @param input
     * @return
     */
    public static String morseToAbc(LinkedList<String> input) {

        StringBuilder result = new StringBuilder();
        int len = input.size();

        for (int i = 0; i < len; i++) {                     //go through every morsecode letter of the sentence
            if (input.get(i).equals(" ")) {                     //new word in the sentence -> add whitespace to result
                result.append(" ");
            } else {
                for (int j = 0; j < morse.length; j++) {    //goes through each element of the variable morse
                    if (input.get(i).equals(morse[j])) {        //which position the morsecode letter has in the variable
                        result.append(letters[j]);          //copies the same position from the letters variable to result
                        break;
                    }
                    if (j == morse.length -1) {             //no match with current morsecode letter -> Error
                        result.append("?");
                    }
                }
            }
        }
        return result.toString();
    }

    /**
     * Expects single word as string to be translated to sound
     * Pass dot or stroke by character to sound function
     *
     * @param INPUT the word you want to convert to morse code sound
     * @throws LineUnavailableException if the line from generateSound (sdl.open(af)) cannot be opened due to resource restrictions
     */
    public static void morseToSound(final String INPUT) throws LineUnavailableException {
        final char[] CODE = INPUT.toCharArray();
        for (char c : CODE) {
            generateSound(c);
        }
    }

    /**
     * Expects an String array of several words or sentences to be translated from morsecode to sound
     *
     * @param INPUT the word you want to convert to morse code sound
     * @throws LineUnavailableException if the line from generateSound (sdl.open(af)) cannot be opened due to resource restrictions
     */
    public static void morseToSound(final String[] INPUT) throws LineUnavailableException {
        for (String c : INPUT) {
            if (c.equals(" ")) {
                try {
                    Thread.sleep(800); // delay to split up chars
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                final char[] MORSECHAR = c.toCharArray();
                for (char morseCharacter : MORSECHAR) {
                    generateSound(morseCharacter);
                }
            }
        }
    }

    /**
     * use the transfer parameter to generate a tone that is either long or short
     *
     * @param C '.' or '-', will be converted to sound
     * @throws LineUnavailableException if the line (sdl.open(af)) cannot be opened due to resource restrictions
     */
    private static void generateSound(final char C) throws LineUnavailableException {
        int duration; // ms
        final int VOL = 1;
        final float SAMPLERATE = 8000f;
        byte[] buf = new byte[1];

        final AudioFormat af = new AudioFormat(
                SAMPLERATE,         // sampling rate per sec
                8,     // memory size for one sample value
                1,          // mono
                true,        // 8bit with sign (+/-)
                false);
        final SourceDataLine sdl = AudioSystem.getSourceDataLine(af);

        if (C == '-') {
            duration = 400;
        } else {
            duration = 100;
        }

        sdl.open(af);
        sdl.start();

        // generate a sinus function
        for (int i = 0; i < duration * 8; i++) {
            double angle = i / (SAMPLERATE / 440) * 2.0 * Math.PI;
            buf[0] = (byte) (Math.sin(angle) * 127.0 * VOL); // current y-value of sinus

            // generate a sound from af that the user can hear
            sdl.write(buf, 0, 1);
        }
        sdl.drain();
        sdl.stop();
        sdl.close();
    }

}