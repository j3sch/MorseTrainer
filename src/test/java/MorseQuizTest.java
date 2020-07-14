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
    public void testIfWordsAreUnique() throws IOException {

        FileReader file = new FileReader("./data/final_list.txt");
        BufferedReader br = new BufferedReader(file);
        ArrayList<String> list = new ArrayList<>();
        String line;

        while ((line = br.readLine()) != null) {
            list.add(line);
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
}
