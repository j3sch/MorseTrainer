import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Unit test for MorseQuiz.
 */
public class MorseQuizTest {

    final String FILE_PATH = "./data/final_list.txt";

    @Test
    public final void testFile() {

        Assert.assertTrue(Files.exists(Paths.get(FILE_PATH)));
        Assert.assertTrue(Files.isReadable(Paths.get(FILE_PATH)));

    }

    @Test
    public final void testIfWordsAreUnique() {

        ArrayList<String> list = getWordsFromFile();

        boolean isUnique = true;

        for (int i = 0; i < list.size(); i++) {
            if (list.lastIndexOf(list.get(i)) != i) {
                System.out.println("Delete one: " + '"' + list.get(i) + '"' + " in " + FILE_PATH);
                isUnique = false;
            }
        }

        Assert.assertTrue(isUnique);
    }

    @Test
    public final void testIfWordAreTranslatable() {

        final ArrayList<String> list = getWordsFromFile();

        boolean isTranslatable = true;

        for (String s : list) {
            String word = Translator.abcToMorse(s);

            if (word.contains("?")) {
                isTranslatable = false;
                System.out.println("Word: " + '"' + s + '"' + " in " + FILE_PATH + " is not translatable");
            }
        }

        Assert.assertTrue(isTranslatable);
    }

    private ArrayList<String> getWordsFromFile() {

        final ArrayList<String> list = new ArrayList<>();

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

        return list;
    }
}
