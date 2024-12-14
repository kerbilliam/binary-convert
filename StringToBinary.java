import java.util.InputMismatchException;
import java.util.Scanner;

public class StringToBinary {
    // Asks for an input from user and returns it as string
    // Type messeage you want to display next to user input line
    // ex: getIntInput("(input): ") --> (input): _
    public static String getStringInput(Scanner console, String inputIndicator) {
        System.out.print(ColoredOutput.GREEN + inputIndicator + ColoredOutput.RESET);
        String userInput = console.nextLine();
        return userInput;
    }

    // Asks explicitly for an int input from user and returns it
    // Type messeage you want to display next to user input line
    // ex: getIntInput("(input): ") --> (input): _
    public static int getIntInput(String inputIndicator) {
        Scanner console = new Scanner(System.in);
        boolean correctInput = false;
        while (!correctInput) {
            try {
                System.out.print(ColoredOutput.GREEN + inputIndicator + ColoredOutput.RESET);
                int userInput = console.nextInt();
                console.close();
                return userInput;
    
            } catch (InputMismatchException e) { // Scanner built in exception
                System.out.println(ColoredOutput.RED + "Invalid input type. Please type an integer." + ColoredOutput.RESET);
                console.reset();
            }
        }
        return 0; // fallback value in case of while loop bug
    }

    // Converts every character within a line of input String to its ASCII binary equivalent
    // NEED TO WORK ON HOW TO KEEP LEADING 0s
    public static String wordToBinary(String input) {
        String binOutput = "";
        for (int i = 0; i < input.length(); i++) {
            binOutput += Integer.toBinaryString((int)input.charAt(i));
            if (i < input.length()) binOutput += " ";
        }
        return binOutput;
    }

    // Asks user if they want to convert strings from file
    // True = Get input from file
    // False = Get input manually
    public static boolean getInputMode(Scanner console) {
        System.out.println("Manual Method: Manually type your input into the terminal.\n" + 
        "Input from File Method: Type the location of a text file into the terminal.");
        System.out.println(ColoredOutput.CYAN + "Default input method is manual");
        System.out.println(ColoredOutput.YELLOW + "Input from file?" + ColoredOutput.RESET);
        String input = getStringInput(console, "(Yes?): ");
        input = input.toLowerCase();

        // Display which method has been chosen
        if (input.charAt(0) == 'y') {
            System.out.println("Input from File Method has been chosen.");
            return true;
        } else {
            System.out.println("Manual Method has been chosen.");
            return false;
        }
    }

    // runs wordToBinary for each word on a line
    public static String binLineConvert(String input) {
        Scanner line = new Scanner(input);
        String binOutput = "";
        while (line.hasNext()) {
            binOutput += wordToBinary(line.next());
            // adds space in binary
            if (line.hasNext()) binOutput += "00100000 ";
        }
        line.close();
        return binOutput;
    }

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        System.out.println("Choose an input method.");
        // If player has chosen to input from file
        if (getInputMode(console)) {
            // function to read from file
        } else {
            System.out.println("Input a string to convert to binary.");
            String input = getStringInput(console, "(input): ");
            System.out.println(binLineConvert(input));
        }

    }
}