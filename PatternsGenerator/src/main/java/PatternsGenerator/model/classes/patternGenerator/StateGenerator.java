package PatternsGenerator.model.classes.patternGenerator;

import PatternsGenerator.model.classes.common.Pattern;
import PatternsGenerator.model.interfaces.patternGenerator.StateGeneratorInterface;
import PatternsGenerator.services.FileIOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class StateGenerator implements StateGeneratorInterface {
    private Pattern pattern;
    private FileIOService fileIOService;
    private List<String> StateSubclasses;
    private String className;
    private String changeStateMethiod;

    public void MenageSubclasses(String input) {
        String[] proceededInput = input.split(",");
        this.setStateSubclasses(new ArrayList<>(Arrays.asList(proceededInput)));
    }

    private String MakeStateTemplate(String fileName) throws IOException {
        return fileIOService.ReadFromFile("patterns/state/" + fileName + ".txt");
    }

    private String MakeSubclassCode(String subclass) throws IOException {
        String subclassCode = this.MakeStateTemplate("stateSubclasses");
        subclassCode = subclassCode.replaceAll("%stateName%", this.getPattern().getName());
        subclassCode = subclassCode.replaceAll("%changeStateMethod%", this.getChangeStateMethiod());
        subclassCode = subclassCode.replaceAll("%subclassName%", subclass);
        subclassCode = this.HandleComments(subclassCode);
        return subclassCode;
    }

    private String HandleComments(String code) throws IOException {
        if (!this.pattern.getAreCommentsIncluded()) {
            code = code.replaceAll("%creationInfo%", "");
            code = code.replaceAll("%emptyFunctionInfo%", "");
        } else {
            code = code.replaceAll("%creationInfo%", fileIOService.ReadFromFile("patterns/common/comments/creationDetails.txt"));
            code = code.replaceAll("%emptyFunctionInfo%", fileIOService.ReadFromFile("patterns/common/comments/emptyFunction.txt"));
        }
        return code;
    }

    @Autowired
    public StateGenerator(FileIOService fileIOService) {
        this.fileIOService = fileIOService;
    }

    @Override
    public String GenerateState() throws IOException {
        String state = this.MakeStateTemplate("state");
        state = state.replaceAll("%stateName%", this.getPattern().getName());
        state = state.replaceAll("%changeStateMethod%", this.getChangeStateMethiod());
        state = state.replaceAll("%className%", this.getClassName());
        state = this.HandleComments(state);
        return state;
    }

    @Override
    public String GenerateStateSubclasses() throws IOException {
        StringBuilder stateSubclassCode = new StringBuilder();
        for (String className : this.getStateSubclasses()) {
            stateSubclassCode.append(this.MakeSubclassCode(className));
        }
        return stateSubclassCode.toString();
    }

    @Override
    public String GenerateClassWithState() throws IOException {
        String classCode = this.MakeStateTemplate("classWithState");
        classCode = classCode.replaceAll("%stateName%", this.getPattern().getName());
        classCode = classCode.replaceAll("%changeStateMethod%", this.getChangeStateMethiod());
        classCode = classCode.replaceAll("%className%", this.getClassName());
        classCode = this.HandleComments(classCode);
        return classCode;
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

    public List<String> getStateSubclasses() {
        return StateSubclasses;
    }

    public void setStateSubclasses(List<String> stateSubclasses) {
        StateSubclasses = stateSubclasses;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getChangeStateMethiod() {
        return changeStateMethiod;
    }

    public void setChangeStateMethiod(String changeStateMethiod) {
        this.changeStateMethiod = changeStateMethiod;
    }

    public String SubclassesToStrings() {
        StringBuilder subclasses = new StringBuilder();
        for(String name: this.getStateSubclasses()) {
            subclasses.append(name).append(",");
        }
        String subclassesTmp = subclasses.toString();
        subclassesTmp = subclassesTmp.substring(0, subclassesTmp.length() - 1);
        return subclassesTmp;
    }
}
