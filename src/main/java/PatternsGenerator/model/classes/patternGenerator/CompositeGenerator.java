package PatternsGenerator.model.classes.patternGenerator;

import PatternsGenerator.model.classes.common.Pattern;
import PatternsGenerator.model.interfaces.patternGenerator.CompositeInterface;
import PatternsGenerator.services.FileIOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class CompositeGenerator implements CompositeInterface {
    private Pattern pattern;
    private String interfaceName;
    private FileIOService fileIOService;
    private List<String> subclasses;

    @Autowired
    public CompositeGenerator(FileIOService fileIOService) {
        this.fileIOService = fileIOService;
    }

    private String HandleComments(String code) throws IOException {
        if(!this.pattern.getAreCommentsIncluded()) {
            code = code.replaceAll("%creationInfo%", "");
            code = code.replaceAll("%crudInfo%", "");
            code = code.replaceAll("%stringInfo%", "");
            code = code.replaceAll("%emptyFunction%", "");
        }
        else {
            code = code.replaceAll("%creationInfo%", fileIOService.ReadFromFile("patterns/common/comments/creationDetails.txt"));
            code = code.replaceAll("%crudInfo%", fileIOService.ReadFromFile("patterns/composite/comments/crudInfo.txt"));
            code = code.replaceAll("%stringInfo%", fileIOService.ReadFromFile("patterns/composite/comments/stringInfo.txt"));
            code = code.replaceAll("%emptyFunction%", fileIOService.ReadFromFile("patterns/common/comments/emptyFunction.txt"));
        }
        return code;
    }

    private String MakeCompositeTemplate(String fileName) throws IOException {
        return fileIOService.ReadFromFile("patterns/composite/" + fileName + ".txt");
    }


    @Override
    public String GenerateInterface() throws IOException {
        String interfaceCode = this.MakeCompositeTemplate("interface");
        interfaceCode = this.HandleComments(interfaceCode);
        interfaceCode = interfaceCode.replaceAll("%interface%", interfaceName);
        return interfaceCode;
    }

    @Override
    public String GenerateComposite() throws IOException {
        String compositeCode = MakeCompositeTemplate("composite");
        compositeCode = HandleComments(compositeCode);
        compositeCode = compositeCode.replaceAll("%interface%", this.pattern.getName());
        compositeCode = compositeCode.replaceAll("%composite%", pattern.getName());
        return  compositeCode;
    }

    @Override
    public String GenerateSubclasses() throws IOException {
        StringBuilder subclassCode = new StringBuilder();
        for(String className: this.getSubclasses()) {
            subclassCode.append(this.MakeSubclass(className));
        }
        return subclassCode.toString();
    }

    private String MakeSubclass(String subclass) throws IOException {
        String subclassCode = this.MakeCompositeTemplate("class");
        subclassCode = this.HandleComments(subclassCode);
        subclassCode = subclassCode.replaceAll("%class%", subclass);
        subclassCode = subclassCode.replaceAll("%interface%", interfaceName);
        return subclassCode;
    }

    public void MenageSubclasses(String input) {
        String [] proceededInput = input.split(",");
        this.setSubclasses(new ArrayList<>(Arrays.asList(proceededInput)));
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

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public FileIOService getFileIOService() {
        return fileIOService;
    }

    public void setFileIOService(FileIOService fileIOService) {
        this.fileIOService = fileIOService;
    }

    public List<String> getSubclasses() {
        return subclasses;
    }

    public void setSubclasses(List<String> subclasses) {
        this.subclasses = subclasses;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }
}
