package Validation;

import java.util.Map;

import com.beust.jcommander.*;


public class ExclusiveInput implements IParametersValidator{
    @Override
    public void validate(Map<String, Object> parameters) throws ParameterException {
        if (parameters.get("--from-file") != null && parameters.get("--input") != null) {
            throw new ParameterException("\033[31monly one input method is valid\033[0m");
        }
    }
}
