package PatternsGenerator.model.interfaces.patternGenerator;

import java.io.IOException;

public interface VisitorGeneratorInterface {
    String GenerateVisitorInterface() throws IOException;
    String GenerateVisitedInterface() throws IOException;
    String GenerateVisitorClass() throws IOException;
    String GenerateVisitedClass() throws IOException;
}
