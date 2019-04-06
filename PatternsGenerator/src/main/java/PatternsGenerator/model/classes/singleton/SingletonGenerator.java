package PatternsGenerator.model.classes.singleton;

import PatternsGenerator.model.classes.common.Attribute;
import PatternsGenerator.model.classes.common.Getter;
import PatternsGenerator.model.classes.common.Pattern;
import PatternsGenerator.model.classes.common.Setter;
import PatternsGenerator.model.interfaces.singleton.SingletonGeneratorInterface;
import PatternsGenerator.services.FileIOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class SingletonGenerator implements SingletonGeneratorInterface {

    private Pattern pattern;
    private List<Attribute> attributes;
    private FileIOService fileIOService;

    @Autowired
    public SingletonGenerator( FileIOService fileIOService) {
        this.fileIOService = fileIOService;
        this.attributes = new ArrayList<>();
    }

    @Override
    public String ToString() throws IOException {
        String singletonString = this.MakeSingletonTemplate();
        singletonString = singletonString.replaceAll("patternName", pattern.getName());
        return singletonString;
    }

    private String MakeSingletonTemplate() throws IOException {
        String SingletonTemplate = fileIOService.ReadFromFile("patterns/singleton/SingletonDeclaration.txt");
        SingletonTemplate = SingletonTemplate + (fileIOService.ReadFromFile("patterns/singleton/SingletonBaseCode.txt"));
        SingletonTemplate = SingletonTemplate.replaceAll("attributes", this.MakeListOfArguments());
        SingletonTemplate = SingletonTemplate.replaceAll("getters", this.MakeListOfGetters());
        SingletonTemplate = SingletonTemplate.replaceAll("setters", this.MakeListOfSetters());
        return SingletonTemplate;
    }

    private String MakeListOfArguments() throws IOException {
        StringBuilder listOfArguments = new StringBuilder();
        for (Attribute attribute : this.attributes) {
            listOfArguments.append(attribute.ToString());
            listOfArguments.append("\n");
        }
        return listOfArguments.toString();
    }

    private String MakeListOfGetters() throws IOException {
        StringBuilder listOfGetters = new StringBuilder();
        for (Attribute attribute : this.attributes) {
            Getter getter = new Getter(new FileIOService());
            getter.setAttribute(attribute);
            listOfGetters.append(getter.ToString());
            listOfGetters.append("\n");
        }
        return listOfGetters.toString();
    }

    private String MakeListOfSetters() throws IOException {
        StringBuilder listOfSetters = new StringBuilder();
        for (Attribute attribute : this.attributes) {
            Setter setter = new Setter(new FileIOService());
            setter.setAttribute(attribute);
            listOfSetters.append(setter.ToString());
            listOfSetters.append("\n");
        }
        return listOfSetters.toString();
    }

    public Pattern getPattern() {
        return pattern;
    }

    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }
}
