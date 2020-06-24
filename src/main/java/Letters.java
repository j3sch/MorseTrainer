import java.util.Scanner;

public class Letters {
    public static void main(String[] args) {
        
        String[] letters = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", };

        String[] morse = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..",
                ".---", "-.-", ".-..", "--", "-.", "---", ".---.", "--.-", ".-.",
                "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--..", ".----",
                "..---", "...--", "....-", ".....", "-....", "--...", "---..", "----.",
                "-----", "--..--", ".-.-.-", "..--.."};

        Scanner sc = new Scanner(System.in);
        String  input = sc.nextLine().toLowerCase() + 0;
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
