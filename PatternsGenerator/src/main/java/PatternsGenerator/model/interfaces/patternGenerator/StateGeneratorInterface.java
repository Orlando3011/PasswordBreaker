package PatternsGenerator.model.interfaces.patternGenerator;

import java.io.IOException;

public interface StateGeneratorInterface {
    String GenerateState() throws IOException;
    String GenerateStateSubclasses() throws IOException;
    String GenerateClassWithState() throws IOException;
}