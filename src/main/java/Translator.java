import java.util.Scanner;

public class Letters {
    public static void main(String[] args) {

        String[] letters = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n",
                            "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1",
                            "2", "3", "4", "5", "6", "7", "8", "9"};

        String[] morse = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..",
                ".---", "-.-", ".-..", "--", "-.", "---", ".---.", "--.-", ".-.",
                "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--..", "-----", ".----",
                "..---", "...--", "....-", ".....", "-....", "--...", "---..", "----."};

        Scanner sc = new Scanner(System.in);
        String  input = sc.nextLine().toLowerCase();
        System.out.println(input);
        int len = input.length();
        String result = "";

        if (len == 0) {
            System.out.println("No Input");
        }

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < letters.length; j++) {
                if (input.substring(i, i+1).equals(letters[j])) {
                    result += morse[j];
                    break;
                }
            }
        }
        System.out.println(result);
    }

}
