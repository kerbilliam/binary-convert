import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import Validation.ExclusiveInput;

@Parameters(parametersValidators = ExclusiveInput.class)
class Args {
    @Parameter(
        names = {"-f", "--from-file"},
        description = "Convert from File"
    )
    private String inputFile;

    @Parameter(
        names = {"-h", "--help", "-?"},
        help = true,
        description = "Display command parameters"
    )
    private boolean help;

    @Parameter(
        names = {"-o", "--output"},
        description = "Specify output file"
    )
    private String outputFile;

    @Parameter(
        names = {"-t", "--text"},
        description = "Convert from text to binary"
    )
    private boolean fromText;

    @Parameter(
        names = {"-b", "--binary"},
        description = "Convert from binary to text"
    )
    private boolean fromBinary;

    @Parameter(
        names = {"-i", "--input"},
        description = "Input into terminal"
    )
    private String terminalInput;

    public boolean fromText() {
        return fromText;
    }

    public boolean fromBinary() {
        return fromBinary;
    }

    public String terminalIn() {
        return terminalInput;
    }

    public String fromFile() {
        return inputFile;
    }

    public String outputFile() {
        return outputFile;
    }

    public boolean isHelp() {
        return help;
    }
}
