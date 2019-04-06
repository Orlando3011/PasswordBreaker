package PatternsGenerator.model.classes.common;

import PatternsGenerator.model.interfaces.common.SetterInterface;
import PatternsGenerator.services.FileIOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Setter implements SetterInterface {
    private Attribute attribute;
    private FileIOService fileIOService;

    public Setter() {}

    @Autowired
    public Setter(FileIOService fileIOService) {
        this.fileIOService = fileIOService;
    }


    @Override
    public String ToString() throws IOException {
        String attributeString = fileIOService.ReadFromFile("patterns/common/setter.txt");
        attributeString = attributeString.replace("type", UpperCase(attribute.getType()));
        attributeString = attributeString.replace("name", UpperCase(attribute.getName()));
        return attributeString;
    }

    @Override
    public String UpperCase(String name) {
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

    public FileIOService getFileIOService() {
        return fileIOService;
    }

    public void setFileIOService(FileIOService fileIOService) {
        this.fileIOService = fileIOService;
    }
}
