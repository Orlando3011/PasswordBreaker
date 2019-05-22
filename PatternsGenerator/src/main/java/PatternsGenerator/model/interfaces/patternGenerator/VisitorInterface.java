package PatternsGenerator.model.interfaces.patternGenerator;

import java.io.IOException;

public interface VisitorInterface {
    String GenerateVisitorInterface() throws IOException;
    String GenerateVisitedInterface() throws IOException;
    String GenerateVisitorClass() throws IOException;
    String GenerateVisitedClass() throws IOException;
}
