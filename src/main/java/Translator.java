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
     *
     * @param input String, the word you want to convert to morse code sound
     * @param status boolean, 0 = input needs to be translated to morse code, 1 = input is already converted to morse code
     * @throws LineUnavailableException
     */
    public static void translateToSound(String input, boolean status) throws LineUnavailableException {
        if (status) { // status = 1 --> morse code liegt vor
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
        } else { // status = 0 --> morse code liegt nicht vor, String muss noch umgewandelt werden
            // todo: convert String to morse code
        }

    }

    /**
     *
     * @param c char, '.' or '-', will be converted to sound
     * @throws LineUnavailableException
     */
    public static void generateSound(char c) throws LineUnavailableException {
        int duration;
        int vol = 1;
        float sound = 8000f;
        byte[] buf = new byte[1];


        AudioFormat af = new AudioFormat(
                        sound,
                        8,
                        1, // mono
                        true,
                        false);
        SourceDataLine sdl = AudioSystem.getSourceDataLine(af);

        if (c == '-') {
            duration = 400;
        } else {
            duration = 100;
        }

        sdl.open(af);
        sdl.start();

        // generate a sound from af that the user can hear
        for (int i = 0; i < duration * 8; i++) {
            double angle = i / (sound / 440) * 2.0 * Math.PI;
            buf[0] = (byte) (Math.sin(angle) * 127.0 * vol);
            sdl.write(buf, 0, 1);
        }
        sdl.drain();
        sdl.stop();
        sdl.close();
    }

}
