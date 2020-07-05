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

        FileReader file = new FileReader("./data/final_list.txt");
        BufferedReader br = new BufferedReader(file);
        ArrayList<String> list = new ArrayList<>();
        String line;

        while ((line = br.readLine()) != null) {
            list.add(line);
        }

        //removes possible empty String
        list.removeIf(word -> word == null || "".equals(word));

        Random rand = new Random();

        int randomIndex = rand.nextInt(list.size());

        return list.get(randomIndex);
    }

    /**
     * A morse quiz which provides a word in morse and four possible Answers.
     *
     * @return a String[] which contains a morse code, the answer, and three wrong answers
     * @throws IOException if final_list.txt could not be found
     */
    public static String[] askForWord() throws IOException {

        String correctAnswer = getRandomLine();

        String wordInMorse = Translator.abcToMorse(correctAnswer);

        return new String[]{wordInMorse, correctAnswer,
                getRandomLine(), getRandomLine(), getRandomLine()};
    }

    /**
     * A morse quiz which provides morse and four possible Answers.
     *
     * @return a String[] which contains a word, the answer, and three wrong answers
     * @throws IOException if final_list.txt could not be found
     */
    public static String[] askForMorse() throws IOException {

        String word = getRandomLine();

        String correctAnswer = Translator.abcToMorse(word);
        String wrongAnswer1 = Translator.abcToMorse(getRandomLine());
        String wrongAnswer2 = Translator.abcToMorse(getRandomLine());
        String wrongAnswer3 = Translator.abcToMorse(getRandomLine());

        return new String[]{word, correctAnswer,
                wrongAnswer1, wrongAnswer2, wrongAnswer3};
    }
}
