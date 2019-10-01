package PatternsGenerator.model.classes.patternGenerator;

import PatternsGenerator.model.classes.common.Pattern;
import PatternsGenerator.model.interfaces.patternGenerator.AdapterInterface;
import PatternsGenerator.services.FileIOService;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AdapterGenerator implements AdapterInterface {

    private Pattern pattern;
    private String firstClassName;
    private String secondClassName;
    private String methodName;
    private String methodReturn;
    private String methodParameters;
    private FileIOService fileIOService;

    public AdapterGenerator(FileIOService fileIOService) {
        this.fileIOService = fileIOService;
    }

    private String MakeAdapterTemplate(String fileName) throws IOException {
        return fileIOService.ReadFromFile("patterns/adapter/" + fileName + ".txt");
    }

    private String HandleComments(String code) throws IOException {
        if(!this.pattern.getAreCommentsIncluded()) {
            code = code.replaceAll("%creationInfo%", "");
            code = code.replaceAll("%emptyFunction%", "");
            code = code.replaceAll("%wrapInfo%", "");
        }
        else {
            code = code.replaceAll("%creationInfo%", fileIOService.ReadFromFile("patterns/common/comments/creationDetails.txt"));
            code = code.replaceAll("%emptyFunction%", fileIOService.ReadFromFile("patterns/common/comments/emptyFunction.txt"));
            code = code.replaceAll("%wrapInfo%", fileIOService.ReadFromFile("patterns/adapter/comments/WrapInfo.txt"));
        }
        return code;
    }

    private String HandleMethodCode(String code) {
        code = code.replaceAll("%adapter%", this.getPattern().getName());
        code = code.replaceAll("%returnType%", this.getMethodReturn());
        code = code.replaceAll("%methodToWrap%", this.getMethodName());
        code = code.replaceAll("%methodArguments%", this.getMethodParameters());
        return code;
    }

    @Override
    public String GenerateInterface() throws IOException {
        String interfaceCode = this.MakeAdapterTemplate("interface");
        interfaceCode = this.HandleComments(interfaceCode);
        interfaceCode = this.HandleMethodCode(interfaceCode);
        return interfaceCode;
    }

    @Override
    public String GenerateFirstClass() throws IOException {
        String classCode = this.MakeAdapterTemplate("class");
        classCode = classCode.replaceAll("%className%", this.getFirstClassName());
        classCode = this.HandleComments(classCode);
        classCode = this.HandleMethodCode(classCode);
        return classCode;
    }

    @Override
    public String GenerateSecondClass() throws IOException {
        String classCode = this.MakeAdapterTemplate("class");
        classCode = classCode.replaceAll("%className%", this.getSecondClassName());
        classCode = this.HandleComments(classCode);
        classCode = this.HandleMethodCode(classCode);
        return classCode;
    }

    @Override
    public String GenerateFirstAdapter() throws IOException {
        String adapterCode = this.MakeAdapterTemplate("adapter");
        adapterCode = adapterCode.replaceAll("%className%", this.getFirstClassName());
        adapterCode = adapterCode.replaceAll("%adapter%", this.getPattern().getName());
        adapterCode = this.HandleComments(adapterCode);
        adapterCode = this.HandleMethodCode(adapterCode);
        return adapterCode;
    }

    @Override
    public String GenerateSecondAdapter() throws IOException {
        String adapterCode = this.MakeAdapterTemplate("adapter");
        adapterCode = adapterCode.replaceAll("%className%", this.getSecondClassName());
        adapterCode = adapterCode.replaceAll("%adapter%", this.getPattern().getName());
        adapterCode = this.HandleComments(adapterCode);
        adapterCode = this.HandleMethodCode(adapterCode);
        return adapterCode;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }

    public String getFirstClassName() {
        return firstClassName;
    }

    public void setFirstClassName(String firstClassName) {
        this.firstClassName = firstClassName;
    }

    public String getSecondClassName() {
        return secondClassName;
    }

    public void setSecondClassName(String secondClassName) {
        this.secondClassName = secondClassName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getMethodReturn() {
        return methodReturn;
    }

    public void setMethodReturn(String methodReturn) {
        this.methodReturn = methodReturn;
    }

    public String getMethodParameters() {
        return methodParameters;
    }

    public void setMethodParameters(String methodParameters) {
        this.methodParameters = methodParameters;
    }

    public FileIOService getFileIOService() {
        return fileIOService;
    }

    public void setFileIOService(FileIOService fileIOService) {
        this.fileIOService = fileIOService;
    }
}
