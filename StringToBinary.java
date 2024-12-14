import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.*;

public class StringToBinary {
    // Asks for an input from user and returns it as string
    // Type messeage you want to display next to user input line
    // ex: getIntInput("(input): ") --> (input): _
    public static String getStringInput(Scanner console, String inputIndicator) {
        System.out.print(StrColor.GREEN + inputIndicator + StrColor.RESET);
        String userInput = console.nextLine();
        return userInput;
    }

    // Asks explicitly for an int input from user and returns it
    // Type messeage you want to display next to user input line
    // ex: getIntInput("(input): ") --> (input): _
    public static int getIntInput(Scanner console, String inputIndicator) {
        while (true) {
            try {
                System.out.print(StrColor.GREEN + inputIndicator + StrColor.RESET);
                int userInput = console.nextInt();
                return userInput;
    
            } catch (InputMismatchException e) { // Scanner built in exception
                System.out.println(StrColor.RED + "Invalid input. Please type an integer." + StrColor.RESET);
                console.nextLine(); // Clear the invalid input from the buffer
            }
        }
    }

    // Converts every character within a line of input String to its ASCII binary equivalent
    // NEED TO WORK ON HOW TO KEEP LEADING 0s
    public static String wordToBinary(String input) {
        String binOutput = "";
        for (int i = 0; i < input.length(); i++) {
            String charBin = Integer.toBinaryString((int)input.charAt(i));
            binOutput += leadingZeros(charBin);
            // binOutput += Integer.toBinaryString((int)input.charAt(i));
            if (i < input.length()) binOutput += " ";
        }
        return leadingZeros(binOutput);
    }

    // returns the binary with the proper amount of leading zeros for a byte
    public static String leadingZeros(String bin) {
        if (bin.length() < 8) {
            int x = 8 - bin.length();
            String out = "";
            for (int i = 0; i < x; i++) out += "0";
            out += bin;
            return out;
        }
        return bin; // else
    }

    // Asks user if they want to convert strings from file
    // True = Get input from file
    // False = Get input manually
    public static boolean getInputMode(Scanner console) {
        System.out.println("Manual Method: Manually type your input into the terminal.\n" + 
        "Input from File Method: Type the location of a text file into the terminal.");
        System.out.println(StrColor.CYAN + "Default input method is manual");
        System.out.println(StrColor.YELLOW + "Input from file?" + StrColor.RESET);
        String input = getStringInput(console, "(Yes?): ");
        input = input.toLowerCase();

        // Display which method has been chosen
        if (input.charAt(0) == 'y') {
            System.out.println("Input from File Method has been chosen.");
            return true;
        } else {
            System.out.println(StrColor.GREEN + "Manual Method has been chosen." + StrColor.RESET);
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

    public static File getFile(Scanner console) {
        while (true) {
            File file = new File(getStringInput(console, "(file): "));
            if (file.exists()) return file;
            else {
                System.out.println(StrColor.RED + "File not found!");
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner console = new Scanner(System.in);

        System.out.println("Choose an input method.");
        // If player has chosen to input from file
        if (getInputMode(console)) {
            // function to read from file
            Scanner file = new Scanner(new File(getStringInput(console, "(file): ")));
        } else {
            System.out.println("Input a string to convert to binary.");
            String input = getStringInput(console, "(input): ");
            System.out.println(binLineConvert(input));
        }

    }
}