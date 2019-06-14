package PatternsGenerator.model.classes.patternGenerator;

import PatternsGenerator.model.classes.common.Pattern;
import PatternsGenerator.model.interfaces.patternGenerator.FactoryGeneratorInterface;
import PatternsGenerator.services.FileIOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class FactoryGenerator implements FactoryGeneratorInterface {
    private Pattern pattern;
    private FileIOService fileIOService;
    private String mainClassName;
    private List<String> subclasses;

    @Autowired
    public FactoryGenerator(FileIOService fileIOService) {
        this.fileIOService = fileIOService;
    }

    @Override
    public String GenerateFactoryInterface() throws IOException {
        String factoryInterface = this.MakeFactoryTemplate("factoryClass");
        factoryInterface = factoryInterface.replaceAll("%factoryName%", this.getPattern().getName());
        factoryInterface = factoryInterface.replaceAll("%mainClassName%", this.getMainClassName());
        factoryInterface = this.HandleComments(factoryInterface);
        return factoryInterface;
    }

    @Override
    public String GenerateSubfactoryClass() throws IOException {
        StringBuilder subfactoryCode = new StringBuilder();
        for(String className: this.getSubclasses()) {
            subfactoryCode.append(this.MakeSubfactory(className));
        }
        return subfactoryCode.toString();
    }

    @Override
    public String GenerateInterface() throws IOException {
        String classInterface = this.MakeFactoryTemplate("mainClass");
        classInterface = this.HandleComments(classInterface);
        classInterface = classInterface.replaceAll("%mainClassName%", this.getMainClassName());
        return classInterface;
    }

    @Override
    public String GenerateSubclass() throws IOException {
        StringBuilder subclassCode = new StringBuilder();
        for(String className: this.getSubclasses()) {
            subclassCode.append(this.MakeSubclass(className));
        }
        return subclassCode.toString();
    }

    public void MenageSubclasses(String input) {
        String [] proceededInput = input.split(",");
        this.setSubclasses(new ArrayList<>(Arrays.asList(proceededInput)));
    }

    private String MakeSubclass(String subclass) throws IOException {
        String subclassCode = this.MakeFactoryTemplate("objectClass");
        subclassCode = this.HandleComments(subclassCode);
        subclassCode = subclassCode.replaceAll("%className%", subclass);
        subclassCode = subclassCode.replaceAll("%mainClassName%", this.getMainClassName());
        return subclassCode;
    }

    private String MakeSubfactory(String subclass) throws IOException {
        String subfactoryCode = this.MakeFactoryTemplate("subfactory");
        subfactoryCode = this.HandleComments(subfactoryCode);
        subfactoryCode = subfactoryCode.replaceAll("%className%", subclass);
        subfactoryCode = subfactoryCode.replaceAll("%factoryName%", this.getPattern().getName());
        subfactoryCode = subfactoryCode.replaceAll("%mainClassName%", this.getMainClassName());
        return subfactoryCode;
    }

    private String MakeFactoryTemplate(String fileName) throws IOException {
        return fileIOService.ReadFromFile("patterns/factory/" + fileName + ".txt");
    }

    private String HandleComments(String code) throws IOException {
        if(!this.pattern.getAreCommentsIncluded()) {
            code = code.replaceAll("%creationInfo%", "");
            code = code.replaceAll("%objectConstructionInfo%", "");
            code = code.replaceAll("%creatingObjectInfo%", "");
        }
        else {
            code = code.replaceAll("%creationInfo%", fileIOService.ReadFromFile("patterns/common/comments/creationDetails.txt"));
            code = code.replaceAll("%objectConstructionInfo%", fileIOService.ReadFromFile("patterns/factory/comments/objectConstructionInfo.txt"));
            code = code.replaceAll("%creatingObjectInfo%", fileIOService.ReadFromFile("patterns/factory/comments/creatingObjectInfo.txt"));
        }
        return code;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }

    public FileIOService getFileIOService() {
        return fileIOService;
    }

    public void setFileIOService(FileIOService fileIOService) {
        this.fileIOService = fileIOService;
    }

    public String getMainClassName() {
        return mainClassName;
    }

    public void setMainClassName(String mainClassName) {
        this.mainClassName = mainClassName;
    }

    public List<String> getSubclasses() {
        return subclasses;
    }

    public void setSubclasses(List<String> subclasses) {
        this.subclasses = subclasses;
    }

    public String SubclassesToStrings() {
        StringBuilder subclasses = new StringBuilder();
        for(String name: this.getSubclasses()) {
            subclasses.append(name).append(",");
        }
        String subclassesTmp = subclasses.toString();
        subclassesTmp = subclassesTmp.substring(0, subclassesTmp.length() - 1);
        return subclassesTmp;
    }
}