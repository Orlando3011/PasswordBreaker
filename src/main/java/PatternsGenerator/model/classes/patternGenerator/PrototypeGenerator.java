package PatternsGenerator.model.classes.patternGenerator;

import PatternsGenerator.model.classes.common.Pattern;
import PatternsGenerator.model.interfaces.patternGenerator.PrototypeGeneratorInterface;
import PatternsGenerator.services.FileIOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class PrototypeGenerator implements PrototypeGeneratorInterface {

    private Pattern pattern;
    private FileIOService fileIOService;
    private String instanceName;
    private String clientName;
    private String copyMethodName;


    @Autowired
    public PrototypeGenerator( FileIOService fileIOService) {
        this.fileIOService = fileIOService;
    }

    @Override
    public String GeneratePrototypeClass() throws IOException {
        String prototypeClass = this.MakePrototypeTemplate("prototypeClass");
        prototypeClass = prototypeClass.replaceAll("%prototype%", this.pattern.getName());
        prototypeClass = this.HandleComments(prototypeClass);
        return prototypeClass;
    }

    @Override
    public String GenerateClientClass() throws IOException {
        String prototypeClient = this.MakePrototypeTemplate("prototypeClient");
        prototypeClient = prototypeClient.replaceAll("%prototype%", this.pattern.getName());
        prototypeClient = prototypeClient.replaceAll("%prototypeClient%", this.getClientName());
        prototypeClient = prototypeClient.replaceAll("%prototypeInstance%", this.getInstanceName());
        prototypeClient = prototypeClient.replaceAll("%makeCopy%", this.getCopyMethodName());
        prototypeClient = this.HandleComments(prototypeClient);
        return prototypeClient;
    }

    private String MakePrototypeTemplate(String fileName) throws IOException {
        return fileIOService.ReadFromFile("patterns/prototype/" + fileName + ".txt");
    }

    private String HandleComments(String code) throws IOException {
        if(!this.pattern.getAreCommentsIncluded()) {
            code = code.replaceAll("%creationInfo%", "");
            code = code.replaceAll("%cloneInfo%", "");
        }
        else {
            code = code.replaceAll("%creationInfo%", fileIOService.ReadFromFile("patterns/common/comments/creationDetails.txt"));
            code = code.replaceAll("%cloneInfo%", fileIOService.ReadFromFile("patterns/prototype/comments/cloneInfo.txt"));
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

    public String getInstanceName() {
        return instanceName;
    }

    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getCopyMethodName() {
        return copyMethodName;
    }

    public void setCopyMethodName(String copyMethodName) {
        this.copyMethodName = copyMethodName;
    }
}
