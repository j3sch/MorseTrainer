import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * MorseQuiz generates random words. Provides the words for the 4 word mode in abc and morse code.
 * @author Firaz Ilhan
 */
public class MorseQuiz {

    /**
     * reads final_list.txt and provides a random word for the quiz.
     * The text file should only have one word in each line,
     * otherwise this method won't work as intended.
     *
     * @return one random word
     */
    public static String getRandomWord() {

        final ArrayList<String> list = new ArrayList<>();
        final String FILE_PATH = "./data/final_list.txt";

        try (final BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {

            String line;

            while ((line = br.readLine()) != null) {
                list.add(line);
            }

        } catch (IOException e1) {
            e1.printStackTrace();
        }

        //removes possible empty String
        list.removeIf(word -> word == null || "".equals(word));

        final Random rand = new Random();
        final int RANDOM_INDEX = rand.nextInt(list.size());
        return list.get(RANDOM_INDEX);
    }

    /**
     * A morse quiz which provides a word in morse and four possible Answers.
     *
     * @return a String[] which contains a morse code, the answer, and three wrong answers
     */
    public static String[] getMorseToWordQuiz() {

        final String CORRECT_ANSWER = getRandomWord();

        final String WORD_IN_MORSE = Translator.abcToMorse(CORRECT_ANSWER);

        return new String[]{WORD_IN_MORSE, CORRECT_ANSWER,
                getRandomWord(), getRandomWord(), getRandomWord()};
    }

    /**
     * A morse quiz which provides morse and four possible Answers.
     *
     * @return a String[] which contains a word, the answer, and three wrong answers
     */
    public static String[] getWordToMorseQuiz() {

        final String WORD = getRandomWord();

        final String CORRECT_ANSWER = Translator.abcToMorse(WORD);
        final String WRONG_ANSWER_1 = Translator.abcToMorse(getRandomWord());
        final String WRONG_ANSWER_2 = Translator.abcToMorse(getRandomWord());
        final String WRONG_ANSWER_3 = Translator.abcToMorse(getRandomWord());

        return new String[]{WORD, CORRECT_ANSWER,
                WRONG_ANSWER_1, WRONG_ANSWER_2, WRONG_ANSWER_3};
    }

    /**
     * provides a default state
     *
     * @return a String[] which contains a random word and its translation
     */
    public static String[] defaultGameWord() {

        final String WORD = getRandomWord();
        final String WORD_IN_MORSE = Translator.abcToMorse(WORD);

        return new String[]{WORD, WORD_IN_MORSE};

    }
}
