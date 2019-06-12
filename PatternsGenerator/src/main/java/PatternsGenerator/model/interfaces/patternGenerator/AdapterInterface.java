package PatternsGenerator.model.interfaces.patternGenerator;

import java.io.IOException;

public interface AdapterInterface {
    String GenerateInterface() throws IOException;
    String GenerateFirstClass() throws IOException;
    String GenerateSecondClass() throws IOException;
    String GenerateFirstAdapter() throws IOException;
    String GenerateSecondAdapter() throws IOException;
}
