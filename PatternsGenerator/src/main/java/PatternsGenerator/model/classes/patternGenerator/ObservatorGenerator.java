package PatternsGenerator.model.classes.patternGenerator;

import PatternsGenerator.model.classes.common.Pattern;
import PatternsGenerator.model.interfaces.patternGenerator.ObservatorInterface;
import PatternsGenerator.services.FileIOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ObservatorGenerator implements ObservatorInterface {

    private Pattern pattern;
    private FileIOService fileIOService;
    private String observatedName;
    private String updateMethodName;
    private String changeStateMethodName;
    private String stateName;
    private String stateType;
    private String observatedState;
    private String observatedStateObject;

    private String makeObservatorTemplate(String fileName) throws IOException {
        return fileIOService.ReadFromFile("patterns/observator/" + fileName + ".txt");
    }

    private String HandleComments(String code) throws IOException {

        if(!this.pattern.getAreCommentsIncluded()) {
            code = code.replaceAll("creationInfo", "");
            code = code.replaceAll("crudInfo", "");
            code = code.replaceAll("informInfo", "");
            code = code.replaceAll("updateInfo", "");
        }
        else {
            code = code.replaceAll("creationInfo", fileIOService.ReadFromFile("patterns/common/comments/creationDetails.txt"));
            code = code.replaceAll("crudInfo", fileIOService.ReadFromFile("patterns/observator/comments/crudInfo.txt"));
            code = code.replaceAll("informInfo", fileIOService.ReadFromFile("patterns/observator/comments/informInfo.txt"));
            code = code.replaceAll("updateInfo", fileIOService.ReadFromFile("patterns/observator/comments/updateInfo.txt"));
        }
        return code;
    }


    @Autowired
    public ObservatorGenerator( FileIOService fileIOService) {
        this.fileIOService = fileIOService;
    }

    @Override
    public String GenerateObservatorInterface() throws IOException {
        String observatorString = this.makeObservatorTemplate("observatorInterface");
        observatorString = observatorString.replaceAll("observator", this.pattern.getName());
        observatorString = observatorString.replaceAll("update", this.updateMethodName);
        observatorString = HandleComments(observatorString);
        return observatorString;
    }

    @Override
    public String GenerateObservatedInterface() throws IOException {
        String observatorString = this.makeObservatorTemplate("observatedInterface");
        observatorString = observatorString.replaceAll("observated", this.observatedName);
        observatorString = observatorString.replaceAll("observator", this.pattern.getName());
        observatorString = HandleComments(observatorString);
        return observatorString;
    }

    @Override
    public String GenerateObservatorClass() throws IOException {
        String observatorString = this.makeObservatorTemplate("observatorClass");
        observatorString = observatorString.replaceAll("observated", this.observatedName);
        observatorString = observatorString.replaceAll("observator", this.pattern.getName());
        observatorString = observatorString.replaceAll("update", this.updateMethodName);
        observatorString = observatorString.replaceAll("changeState", this.changeStateMethodName);
        observatorString = observatorString.replaceAll("stateName", this.stateName);
        observatorString = observatorString.replaceAll("stateType", this.stateType);
        observatorString = observatorString.replaceAll("observatedState", this.observatedState);
        observatorString = observatorString.replaceAll("observatedStateObject", this.observatedStateObject);
        observatorString = HandleComments(observatorString);
        return observatorString;
    }

    @Override
    public String GenerateObservatedClass() throws IOException {
        String observatorString = this.makeObservatorTemplate("observatedClass");
        observatorString = observatorString.replaceAll("observated", this.observatedName);
        observatorString = observatorString.replaceAll("observator", this.pattern.getName());
        observatorString = observatorString.replaceAll("update", this.updateMethodName);
        observatorString = observatorString.replaceAll("observatedState", this.observatedState);
        observatorString = observatorString.replaceAll("observatedStateObject", this.observatedStateObject);
        observatorString = HandleComments(observatorString);
        return observatorString;
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

    public String getObservatedName() {
        return observatedName;
    }

    public void setObservatedName(String observatedName) {
        this.observatedName = observatedName;
    }

    public String getUpdateMethodName() {
        return updateMethodName;
    }

    public void setUpdateMethodName(String updateMethodName) {
        this.updateMethodName = updateMethodName;
    }

    public String getChangeStateMethodName() {
        return changeStateMethodName;
    }

    public void setChangeStateMethodName(String changeStateMethodName) {
        this.changeStateMethodName = changeStateMethodName;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getStateType() {
        return stateType;
    }

    public void setStateType(String stateType) {
        this.stateType = stateType;
    }

    public String getObservatedState() {
        return observatedState;
    }

    public void setObservatedState(String observatedState) {
        this.observatedState = observatedState;
    }

    public String getObservatedStateObject() {
        return observatedStateObject;
    }

    public void setObservatedStateObject(String observatedStateObject) {
        this.observatedStateObject = observatedStateObject;
    }
}

