import org.junit.Assert;
import org.junit.Test;
import java.util.Arrays;
import java.util.LinkedList;


import static org.junit.Assert.assertTrue;

/**
 * Unit test for Translator.
 */
public class TranslatorTest {
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
        Assert.assertEquals("", Translator.abcToMorse("".toCharArray()));
        Assert.assertEquals("/ / / / / / / / / / ", Translator.abcToMorse("          ".toCharArray()));
        Assert.assertEquals("- .... .. ... / .. ... / .- / - . ... - -.-.--", Translator.abcToMorse("This is a test!".toCharArray()));
        Assert.assertEquals("- .... . / - .-. .- -. ... .-.. .- - .. --- -. / ..-. . . / .. ... / ...-- -.... ..... --..-- ----. ---.. / . ..- .-. --- ...", Translator.abcToMorse("The translation fee is 365,98 Euros".toCharArray()));
        Assert.assertEquals("..- -. .- -.-. -.-. . .--. - .- -... .-.. . / -.-. .... .- .-. .- -.-. - . .-. ... / .-.. .. -.- . / ? ? ? ? ? ? / .-- .. .-.. .-.. / -... . / -.. .. ... .--. .-.. .- -.-- . -.. /" +
                " .- ... / --.- ..- . ... - .. --- -. " + "/ -- .- .-. -.- ...", Translator.abcToMorse("unacceptable characters like %;#<>* will be displayed as question marks".toCharArray()));
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

        LinkedList<String> test = new LinkedList<String>(Arrays.asList("-", "....", "..", "...", " ", "..", "...", " ", ".-", " ", "-", ".", "...", "-", "-.-.--"));
        Assert.assertEquals("this is a test!", Translator.morseToAbc(test));

        LinkedList<String> fee = new LinkedList<String>(Arrays.asList("-", "....", ".", " ", "-", ".-.", ".-", "-.", "...", ".-..", ".-", "-", "..", "---", "-.", " ", "..-.", ".", ".", " ", "..", "...", " ", "...--", "-....",
                ".....", "--..--", "----.", "---..", " ", ".", "..-", ".-.", "---", "..."));
        Assert.assertEquals("the translation fee is 365,98 euros", Translator.morseToAbc(fee));

        LinkedList<String> unacceptableChar = new LinkedList<String>(Arrays.asList("..-", "-.", ".-", "-.-.", "-.-.", ".", ".--.", "-", ".-", "-...", ".-..", ".", " ", "-.-.", "....", ".-", ".-.", ".-", "-.-.", "-", ".", ".-.", "...", " ", ".-..", "..", "-.-", ".", " ", "%",
                ";", "#", "<", ">", "*", " ", ".--", "..", ".-..", ".-..", " ", "-...", ".", " ", "-..", "..", "...", ".--.", ".-..", ".-", "-.--", ".", "-..", " ", ".-", "...", " ", "--.-", "..-", ".", "...", "-", "..", "---", "-.", " ", "--", ".-", ".-.", "-.-", "..."));
        Assert.assertEquals("unacceptable characters like ?????? will be displayed as question marks", Translator.morseToAbc(unacceptableChar));

        LinkedList<String> alphabetical = new LinkedList<String>(Arrays.asList("this", " ", "sentence", " ", "is", " ", "alphabetical"));
        Assert.assertEquals("? ? ? ?", Translator.morseToAbc(alphabetical));

    }
}
