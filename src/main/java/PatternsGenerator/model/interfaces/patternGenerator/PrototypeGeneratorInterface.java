package PatternsGenerator.model.interfaces.patternGenerator;

import java.io.IOException;

public interface PrototypeGeneratorInterface {
    String GeneratePrototypeClass() throws IOException;
    String GenerateClientClass() throws IOException;
}
