package PatternsGenerator.model.interfaces.patternGenerator;

import java.io.IOException;
import java.util.List;

public interface FactoryGeneratorInterface {
    String GenerateFactoryInterface() throws IOException;
    String GenerateSubfactoryClass() throws IOException;

    String GenerateInterface() throws IOException;
    String GenerateSubclass() throws IOException;

    void MenageSubclasses(String input);
    void setSubclasses(List<String> subclasses);
}
