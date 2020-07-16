import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class MorseQuizTest {

    @Test
    public void testFile() {
        Assert.assertTrue(Files.exists(Paths.get("./data/final_list.txt")));
        Assert.assertTrue(Files.isReadable(Paths.get("./data/final_list.txt")));

    }

    @Test
    public void testIfWordsAreUnique() {

        ArrayList<String> list = new ArrayList<>();

        final String filePath = "./data/final_list.txt";

        try (final BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                list.add(line);
            }

        } catch (IOException e1) {
            e1.printStackTrace();
        }

        //removes possible empty String
        list.removeIf(word -> word == null || "".equals(word));

        boolean isUnique = true;

        for (int i = 0; i < list.size(); i++) {
            if (list.lastIndexOf(list.get(i)) != i) {
                System.out.println("Delete one: " + '"' + list.get(i) + '"' + " in text file");
                isUnique = false;
            }
        }

        Assert.assertTrue(isUnique);
    }

    @Test
    public void getMorseToWordQuizTest() {

        String[] a1 = MorseQuiz.getMorseToWordQuiz();

        final String WORD_IN_MORSE = a1[0];
        final String CORRECT_ANSWER = a1[1];

        Assert.assertTrue(WORD_IN_MORSE.matches("[.-]+"));
        Assert.assertFalse(CORRECT_ANSWER.matches("[.-]+"));
        Assert.assertEquals(WORD_IN_MORSE, Translator.abcToMorse(CORRECT_ANSWER));
    }

    @Test
    public void getWordToMorseQuiz() {

        String[] a2 = MorseQuiz.getWordToMorseQuiz();

        final String WORD =  a2[0];
        final String CORRECT_ANSWER = a2[1];

        Assert.assertFalse(WORD.matches("[.-]+"));
        Assert.assertTrue(CORRECT_ANSWER.matches("[.-]+"));
        Assert.assertEquals(CORRECT_ANSWER, Translator.abcToMorse(WORD));
    }

    @Test
    public void defaultGameWordTest() {

        String[] a3 = MorseQuiz.defaultGameWord();

        final String WORD = a3[0];
        final String WORD_IN_MORSE = a3[1];

        Assert.assertFalse(WORD.matches("[.-]+"));
        Assert.assertTrue(WORD_IN_MORSE.matches("[.-]+"));
        Assert.assertEquals(WORD_IN_MORSE, Translator.abcToMorse(WORD));
    }
}
