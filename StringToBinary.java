import java.util.Scanner;

public class StringToBinary {
    public static String getInput() {
        Scanner console = new Scanner(System.in);
        System.out.print(ColoredOutput.GREEN + "(input): " + ColoredOutput.RESET);
        String userInput = console.nextLine();
        console.close();
        return userInput;
    }

    public static String wordToBinary(String input) {
        String binOutput = "";
        for (int i = 0; i < input.length(); i++) {
            binOutput += Integer.toBinaryString((int)input.charAt(i));
            if (i < input.length()) binOutput += " ";
        }
        return binOutput;
    }

    public static void main(String[] args) {
        System.out.println("Input a string to convert to binary.");
        System.out.println(wordToBinary(getInput()));

    }
}