import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class MorseQuiz {

    /**
     * reads final_list.txt and provides a random word for the quiz.
     * The text file should only have one word in each line,
     * otherwise this method won't work as intended.
     *
     * @return one random word
     * @throws IOException if final_list.txt could not be found
     */
    public static String getRandomWord() throws IOException {

        final FileReader file = new FileReader("./data/final_list.txt");
        final BufferedReader br = new BufferedReader(file);
        ArrayList<String> list = new ArrayList<>();
        String line;

        while ((line = br.readLine()) != null) {
            list.add(line);
        }

        //removes possible empty String
        list.removeIf(word -> word == null || "".equals(word));

        final Random rand = new Random();

        final int RANDOM_INDEX = rand.nextInt(list.size());

        file.close();
        return list.get(RANDOM_INDEX);
    }

    /**
     * A morse quiz which provides a word in morse and four possible Answers.
     *
     * @return a String[] which contains a morse code, the answer, and three wrong answers
     * @throws IOException if final_list.txt could not be found
     */
    public static String[] getMorseToWordQuiz() throws IOException {

        final String CORRECT_ANSWER = getRandomWord();

        final String WORD_IN_MORSE = Translator.abcToMorse(CORRECT_ANSWER);

        return new String[]{WORD_IN_MORSE, CORRECT_ANSWER,
                getRandomWord(), getRandomWord(), getRandomWord()};
    }

    /**
     * A morse quiz which provides morse and four possible Answers.
     *
     * @return a String[] which contains a word, the answer, and three wrong answers
     * @throws IOException if final_list.txt could not be found
     */
    public static String[] getWordToMorseQuiz() throws IOException {

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
     * @throws IOException if final_list.txt could not be found
     */
    public static String[] defaultGameWord() throws IOException {

        final String WORD = getRandomWord();
        final String WORD_IN_MORSE = Translator.abcToMorse(WORD);

        return new String[]{WORD, WORD_IN_MORSE};

    }
}
