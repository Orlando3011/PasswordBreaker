package PatternsGenerator.model.classes;

import PatternsGenerator.model.interfaces.AttributeInterface;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


@Component
public class Attribute implements AttributeInterface {
    private String type;
    private String name;

    public Attribute() {}

    @Override
    public String ToString() throws IOException {
        String fileName = "patterns/common/attribute.txt";
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        return new String(Files.readAllBytes(file.toPath()));
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
