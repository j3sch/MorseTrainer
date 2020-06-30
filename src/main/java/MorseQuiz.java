import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class MorseQuiz {

    /**
     * reads a random line in final_list_txt.
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
     * @return return true if user answered correctly
     * @throws IOException if final_list.txt could not be found
     */
    public static boolean askForWord() throws IOException {
        String correctAnswer = readRandomLine();

        String wordInMorse = MorseConverter.translateToMorse(correctAnswer); // show to player

        String[] possibleAnswers = {correctAnswer, readRandomLine(), readRandomLine(), readRandomLine()};

        Collections.shuffle(Arrays.asList(possibleAnswers));

        String a = possibleAnswers[0]; //show to player
        String b = possibleAnswers[1]; //show to player
        String c = possibleAnswers[2]; //show to player
        String d = possibleAnswers[3]; //show to player

        try (final Scanner scan = new Scanner(System.in)) {

            String userInput = scan.next();
            return userInput.equalsIgnoreCase(correctAnswer);
        }
    }

    /**
     * A morse quiz which provides morse and four possible Answers.
     * @return return true if user answered correctly
     * @throws IOException if final_list.txt could not be found
     */
    public static boolean askForMorse() throws IOException {

        String word = readRandomLine(); //show to player

        String correctAnswer = MorseConverter.translateToMorse(word);
        String wrongAnswer1 = MorseConverter.translateToMorse(readRandomLine());
        String wrongAnswer2 = MorseConverter.translateToMorse(readRandomLine());
        String wrongAnswer3 = MorseConverter.translateToMorse(readRandomLine());

        String[] possibleAnswers = {correctAnswer, wrongAnswer1,
                wrongAnswer2, wrongAnswer3};

        Collections.shuffle(Arrays.asList(possibleAnswers));

        String a = possibleAnswers[0]; //show to player
        String b = possibleAnswers[1]; //show to player
        String c = possibleAnswers[2]; //show to player
        String d = possibleAnswers[3]; //show to player

        try (final Scanner scan = new Scanner(System.in)) {
            String userInput = scan.nextLine();
            return userInput.equals(correctAnswer);
        }
    }
}
