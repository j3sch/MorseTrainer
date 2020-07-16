import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class TranslatorTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    // Jens Tests
    // todo: write tests for your stuff
    @Test public void test_abcToMorse_words() {
        Assert.assertEquals("-. ..- .-.. .-..", Translator.abcToMorse("null"));
        Assert.assertEquals(".- -... -.-. -.. . ..-. --. .... .. .--- -.- .-.. -- -. --- .--. --.- .-. ... - ..- ...- .-- -..- -.-- --..", Translator.abcToMorse("abcdefghijklmnopqrstuvwxyz"));
        Assert.assertEquals("----- .---- ..--- ...-- ....- ..... -.... --... ---.. ----.", Translator.abcToMorse("0123456789"));
        Assert.assertEquals(".... .- ? .-.. .-.. ? ---", Translator.abcToMorse("ha/ll-o"));

    }

    @Test public void test_abcToMorse_sentence() {
        Assert.assertEquals("-.. .- ... / .... .. . .-. / .. ... - / . .. -. / - . ... - -.-.--", Translator.abcToMorse("das hier ist ein test!".toCharArray()));

    }

    @Test public void test_morseToAbc_word() {
        Assert.assertEquals("null", Translator.morseToAbc("-. ..- .-.. .-.."));
    }

    @Test public void test_morseToAbc_sentence() {

    }
    // End of Jens Test

    // Firaz Tests
    // todo: write tests for your stuff
    // End of Firaz Test

    // Andrea Tests
    // todo: write tests for your stuff
    // End of Andrea Test

    // Max Tests
    // todo: write tests for your stuff
    // End of Max Test
}
