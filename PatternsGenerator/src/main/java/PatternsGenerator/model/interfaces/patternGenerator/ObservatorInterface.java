package PatternsGenerator.model.interfaces.patternGenerator;

import java.io.IOException;

public interface ObservatorInterface {
    String GenerateObservatorInterface() throws IOException;
    String GenerateObservatedInterface() throws IOException;
    String GenerateObservatorClass() throws IOException;
    String GenerateObservatedClass() throws IOException;
}
