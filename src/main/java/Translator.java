import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import java.util.Scanner;

public class Translator {
    public static void main(String[] args) {

        String[] letters = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n",
                "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1",
                "2", "3", "4", "5", "6", "7", "8", "9"};

        String[] morse = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..",
                ".---", "-.-", ".-..", "--", "-.", "---", ".---.", "--.-", ".-.",
                "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--..", "-----", ".----",
                "..---", "...--", "....-", ".....", "-....", "--...", "---..", "----."};

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine().toLowerCase();
        System.out.println(input);
        int len = input.length();
        String result = "";

        if (len == 0) {
            System.out.println("No Input");
        }

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < letters.length; j++) {
                if (input.substring(i, i + 1).equals(letters[j])) {
                    result += morse[j];
                    break;
                }
            }
        }
        System.out.println(result);
    }

    /**
     * Expects single word as string to be translated to sound
     * Pass dot or stroke by character to sound function
     *
     * @param input the word you want to convert to morse code sound
     * @throws LineUnavailableException if the line from generateSound (sdl.open(af)) cannot be opened due to resource restrictions
     */
    public static void morseToSound(String input) throws LineUnavailableException {
        char[] code = input.toCharArray();
        for (char c : code) {
            generateSound(c);
        }
    }

    /**
     * Expects an String array of several words or sentences to be translated from morsecode to sound
     *
     * @param input the word you want to convert to morse code sound
     * @throws LineUnavailableException if the line from generateSound (sdl.open(af)) cannot be opened due to resource restrictions
     */
    public static void morseToSound(String[] input) throws LineUnavailableException {
        for (String c : input) {
            if (c.equals(" ")) {
                try {
                    Thread.sleep(800); // delay to split up chars
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                char[] morseChar = c.toCharArray();
                for (char morseCharacter : morseChar) {
                    generateSound(morseCharacter);
                }
            }
        }
    }

    /**
     * use the transfer parameter to generate a tone that is either long or short
     *
     * @param c '.' or '-', will be converted to sound
     * @throws LineUnavailableException if the line (sdl.open(af)) cannot be opened due to resource restrictions
     */
    private static void generateSound(char c) throws LineUnavailableException {
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
