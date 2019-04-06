package PatternsGenerator.model.classes.common;

import PatternsGenerator.model.interfaces.common.AttributeInterface;
import PatternsGenerator.services.FileIOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.IOException;


@Component
public class Attribute implements AttributeInterface {
    private String type;
    private String name;
    private FileIOService fileIOService;

    public Attribute() {}

    @Autowired
    public Attribute(FileIOService fileIOService) {
        this.fileIOService = fileIOService;
    }

    public Attribute(FileIOService fileIOService, String type, String name) {
        this.fileIOService = fileIOService;
        this.type = type;
        this.name = name;
    }

    @Override
    public String ToString() throws IOException {
        String attributeString = fileIOService.ReadFromFile("patterns/common/attribute.txt");
        attributeString = attributeString.replace("type", type);
        attributeString = attributeString.replace("name", name);
        return attributeString;
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
