import java.io.FileNotFoundException;
import java.util.Scanner;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;

public class Main {

    final Args mainArgs = new Args();
    public static void main (String[] args) throws FileNotFoundException {
        Main instance = new Main();
        instance.handleArgs(args);
        instance.run();
    }    

    void handleArgs (String args[]) {
        JCommander jcomm = new JCommander(mainArgs);
        jcomm.setProgramName("binary-convert");

        try {
            jcomm.parse(args);
        } catch (ParameterException exception) {
            System.out.println(exception.getMessage());
            System.exit(0);
        }

        if (mainArgs.isHelp()) {
            showUsage(jcomm);
            System.exit(0);
        }


    }

    void showUsage(JCommander jcomm) {
        jcomm.usage();
        System.exit(0);
    }

    void run() throws FileNotFoundException {
        if (mainArgs.fromText()) {
            if (isOutputToFile()){
            outputFile(convertToBinary());
            } else {
                outputToTerminal(convertToBinary());
            }
        }
    }

    String convertToBinary() throws FileNotFoundException {
        if (isFromFile()) {
            Scanner inputScanner = StringToBinary.getFile(mainArgs.fromFile());
            return StringToBinary.binFileConvert(inputScanner);
        } else {
            return StringToBinary.binLineConvert(mainArgs.terminalIn());
        }
    }

    void outputFile(String converted_output) throws FileNotFoundException {
        StringToBinary.strToFile(converted_output, mainArgs.outputFile());
        System.out.println("Output Printed to File");
    }

    void outputToTerminal(String converted_output) {
        System.out.println(converted_output);
    }

    boolean isOutputToFile() {
        return mainArgs.outputFile() != null;
    }

    boolean isFromFile() {
        return mainArgs.fromFile() != null;
    }
}
