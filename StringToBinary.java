import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.*;

public class StringToBinary {
    private static final String DEFAULT_FILE_OUTPUT = "outputFiles/out.txt";
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
        System.out.println("Choose an input method.");
        System.out.println("Manual Method: Manually type your input into the terminal.\n" + 
        "Input from File Method: Type the location of a text file into the terminal.");
        System.out.println(StrColor.CYAN + "Default input method is manual");
        System.out.println(StrColor.YELLOW + "Input from file?" + StrColor.RESET);
        String input = getStringInput(console, "(Yes?): ");
        input = input.toLowerCase();

        // Display which method has been chosen
        try {
            if (input.charAt(0) == 'y') {
                System.out.println("Input from File Method has been chosen.");
                return true;
            } else {
                System.out.println(StrColor.GREEN + "Manual Method has been chosen." + StrColor.RESET);
                return false;
            }
    
        } catch (StringIndexOutOfBoundsException e) {
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

    public static Scanner getFile(Scanner console) {
        while (true) {
            try {
                Scanner file = new Scanner(new File(getStringInput(console, "(file): ")));
                return file;
            } catch (FileNotFoundException e) {
                System.out.println(StrColor.RED + "File not found!" + StrColor.RESET);
            }
        }
    }

    public static String binFileConvert(Scanner file) {
        String output = "";
        while (file.hasNextLine()) {
            output += binLineConvert(file.nextLine());
            if (file.hasNextLine()) output += "\n";
        }
        return output;
    }

    // Asks user if they want to output to a file
    // True = output to file
    // False = output to terminal
    public static boolean getOutputMode(Scanner console) {
        System.out.println("Choose an output method.");
        System.out.println(StrColor.CYAN + "Default output is to terminal." + StrColor.RESET);
        System.out.println(StrColor.YELLOW + "Output to file?" + StrColor.RESET);
        String input = getStringInput(console, "(Yes?): ").toLowerCase();
        try {
            if (input.charAt(0) == 'y') return true;
            else return false;
    
        } catch (StringIndexOutOfBoundsException e) {
            return false;
        }
    }

    public static void strToFile(Scanner console, String in) throws FileNotFoundException{
        String filePath = getStringInput(console, "(output file): ");
        if (filePath.isEmpty()) filePath = DEFAULT_FILE_OUTPUT;
        PrintStream output = new PrintStream(new File(filePath));
        output.print(in);
        output.close();


        // try {
        //     PrintStream output = new PrintStream(new File(getStringInput(console, "(output file): ")));
        //     output.print(in);
        //     output.close();
    
        // } catch (FileNotFoundException e) {
        //     PrintStream output = new PrintStream(new File(DEFAULT_FILE_OUTPUT));
        //     output.print(in);
        //     output.close();
        // } 
    }

    public static void main(String[] args) throws FileNotFoundException{
        Scanner console = new Scanner(System.in);

        if (getInputMode(console)) {
            // convert from file
            Scanner inputFile = getFile(console);
            String output = binFileConvert(inputFile);
            // outputs to file or terminal
            if (getOutputMode(console)) {
                System.out.println("Input existing or new file location.");
                System.out.println(StrColor.CYAN + "Default file location: '" + StrColor.YELLOW + DEFAULT_FILE_OUTPUT + "'" + StrColor.RESET);
                strToFile(console, output);
                System.out.println("Result printed to file.");
            } else System.out.println(output);

        } else {
            // convert from terminal input
            System.out.println("Input a string to convert to binary.");
            String input = getStringInput(console, "(input): ");
            String output = binLineConvert(input);
            // outputs to file or terminal
            if (getOutputMode(console)) {
                strToFile(console, output);
                System.out.println("Result printed to file.");
            } else System.out.println(output);
        }

    }
}