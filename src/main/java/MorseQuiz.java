import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class MorseQuiz {

    /**
     * reads a random line in final_list_txt.
     *
     * @return one random word
     * @throws IOException if final_list.txt could not be found
     */
    public static String getRandomLine() throws IOException {
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
     * @return a String[] which contains a morse code, the answer, and three wrong answers
     * @throws IOException if final_list.txt could not be found
     */
    public static String[] askForWord() throws IOException {
        String correctAnswer = getRandomLine();

        String wordInMorse = Translator.abcToMorse(correctAnswer);

        return new String[]{wordInMorse, correctAnswer,
                getRandomLine(), getRandomLine(), getRandomLine()};

       /* Collections.shuffle(Arrays.asList(possibleAnswers));

        String a = possibleAnswers[0];
        String b = possibleAnswers[1];
        String c = possibleAnswers[2];
        String d = possibleAnswers[3];

        try (final Scanner scan = new Scanner(System.in)) {

            String userInput = scan.next();
            return userInput.equalsIgnoreCase(correctAnswer);
        }*/
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

       /* Collections.shuffle(Arrays.asList(possibleAnswers));

        String a = possibleAnswers[0];
        String b = possibleAnswers[1];
        String c = possibleAnswers[2];
        String d = possibleAnswers[3];

        try (final Scanner scan = new Scanner(System.in)) {
            String userInput = scan.nextLine();
            return userInput.equals(correctAnswer);
        }*/
    }
}
