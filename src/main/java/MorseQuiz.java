import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class MorseQuiz {

    /**
     * reads a random line in final_list_txt.
     *
     * @return one random word
     * @throws IOException if final_list.txt could not be found
     */
    public static String readRandomLine() throws IOException {
        FileReader file = new FileReader("./data/final_list.txt");
        BufferedReader br = new BufferedReader(file);
        ArrayList<String> array = new ArrayList<>();
        String line;

        while ((line = br.readLine()) != null) {
            array.add(line);
        }
        Random rand = new Random();

        int randomIndex = rand.nextInt(array.size());

        return array.get(randomIndex);
    }

    /**
     * A morse quiz which provides a word in morse and four possible Answers.
     *
     * @return return true if user answered correctly
     * @throws IOException if final_list.txt could not be found
     */
    public static boolean askForWord() throws IOException {
        String word = readRandomLine();

        StringBuilder wordInMorse = Translator.translateToMorse(word);

        String[] possibleAnswers = {word, readRandomLine(), readRandomLine(), readRandomLine()};

        Collections.shuffle(Arrays.asList(possibleAnswers));

        String a = possibleAnswers[0];
        String b = possibleAnswers[1];
        String c = possibleAnswers[2];
        String d = possibleAnswers[3];

        try (final Scanner scan = new Scanner(System.in)) {

            String userInput = scan.next().toLowerCase();
            String wordLow = word.toLowerCase();
            return userInput.equals(wordLow);
        }
    }
}
