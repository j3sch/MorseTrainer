import org.junit.Assert;
import org.junit.Test;
import sun.tools.jstat.Token;

import java.lang.reflect.Array;
import java.util.ArrayList;

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
        Assert.assertEquals("", Translator.abcToMorse(""));
        Assert.assertEquals(".-- . .-.. -.-. --- -- .", Translator.abcToMorse("welcome"));
        Assert.assertEquals(".- -... -.-. -.. . ..-. --. .... .. .--- -.- .-.. -- -. --- .--. --.- .-. ... - ..- ...- .-- -..- -.-- --..", Translator.abcToMorse("abcdefghijklmnopqrstuvwxyz"));
        Assert.assertEquals("----- .---- ..--- ...-- ....- ..... -.... --... ---.. ----.", Translator.abcToMorse("0123456789"));
        Assert.assertEquals("--..-- .-.-.- ..--.. -.-.--", Translator.abcToMorse(",.?!"));
        Assert.assertEquals(".... . ? .-.. .-.. ? ---", Translator.abcToMorse("he/ll-o"));
        Assert.assertEquals("--- -. .-.. -.-- ? .-- --- .-. -.. ... ? .-- .. .-.. .-.. ? - .-. .- -. ... .-.. .- - . -..", Translator.abcToMorse("only words will translated"));

    }

    @Test public void test_abcToMorse_sentence() {
        Assert.assertEquals("", Translator.abcToMorse("".toLowerCase().toCharArray()));
        Assert.assertEquals("/ / / / / / / / / / ", Translator.abcToMorse("          ".toLowerCase().toCharArray()));
        Assert.assertEquals("- .... .. ... / .. ... / .- / - . ... - -.-.--", Translator.abcToMorse("This is a test!".toLowerCase().toCharArray()));
        Assert.assertEquals("- .... . / - .-. .- -. ... .-.. .- - .. --- -. / ..-. . . / .. ... / ...-- -.... ..... --..-- ----. ---.. / . ..- .-. --- ...", Translator.abcToMorse("The translation fee is 365,98 Euros".toLowerCase().toCharArray()));
        Assert.assertEquals("..- -. .- -.-. -.-. . .--. - .- -... .-.. . / -.-. .... .- .-. .- -.-. - . .-. ... / .-.. .. -.- . / ? ? ? ? ? ? / .-- .. .-.. .-.. / -... . / -.. .. ... .--. .-.. .- -.-- . -.. /" +
                " .- ... / --.- ..- . ... - .. --- -. " + "/ -- .- .-. -.- ...", Translator.abcToMorse("unacceptable characters like -;#<>* will be displayed as question marks".toLowerCase().toCharArray()));
    }

    @Test public void test_morseToAbc_word() {
        Assert.assertEquals("", Translator.morseToAbc(""));
        Assert.assertEquals("welcome", Translator.morseToAbc(".-- . .-.. -.-. --- -- ."));
        Assert.assertEquals("abcdefghijklmnopqrstuvwxyz", Translator.morseToAbc(".- -... -.-. -.. . ..-. --. .... .. .--- -.- .-.. -- -. --- .--. --.- .-. ... - ..- ...- .-- -..- -.-- --.."));
        Assert.assertEquals("0123456789", Translator.morseToAbc("----- .---- ..--- ...-- ....- ..... -.... --... ---.. ----."));
        Assert.assertEquals(",.?!", Translator.morseToAbc("--..-- .-.-.- ..--.. -.-.--"));
        Assert.assertEquals("?", Translator.morseToAbc("hello"));
        Assert.assertEquals("only?words?will?translated", Translator.morseToAbc("--- -. .-.. -.-- / .-- --- .-. -.. ... / .-- .. .-.. .-.. / - .-. .- -. ... .-.. .- - . -.."));

    }

    @Test public void test_morseToAbc_sentence() {
        Assert.assertEquals("", Translator.morseToAbc(""));
        Assert.assertEquals("welcome", Translator.morseToAbc(".-- . .-.. -.-. --- -- ."));
        Assert.assertEquals("this is a test!", Translator.morseToAbc(ArrayList<String> eine = new ArrayList<String>("--..-- .-.-.- ..--.. -.-.--")));


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
