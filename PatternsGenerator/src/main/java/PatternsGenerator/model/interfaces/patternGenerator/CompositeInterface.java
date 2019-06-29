package PatternsGenerator.model.interfaces.patternGenerator;

import java.io.IOException;

public interface CompositeInterface {
    String GenerateInterface() throws IOException;
    String GenerateComposite() throws IOException;
    String GenerateSubclasses() throws IOException;
}
