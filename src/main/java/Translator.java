import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class Translator {
    public static String abcToMorse(String word) {

        String[] letters = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n",
                "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1",
                "2", "3", "4", "5", "6", "7", "8", "9"};

        String[] morse = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..",
                ".---", "-.-", ".-..", "--", "-.", "---", ".---.", "--.-", ".-.",
                "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--..", "-----", ".----",
                "..---", "...--", "....-", ".....", "-....", "--...", "---..", "----."};
        
        int len = word.length();
        StringBuilder result = new StringBuilder();
        char[] wordArray = word.toCharArray();

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < letters.length; j++) {
                if (wordArray[i].equals(letters[j])) {
                    result.append(morse[j]);
                    break;
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