package PatternsGenerator.model.classes.patternGenerator;

import PatternsGenerator.model.classes.common.Pattern;
import PatternsGenerator.model.interfaces.patternGenerator.VisitorInterface;
import PatternsGenerator.services.FileIOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class VisitorGenerator implements VisitorInterface {

    private Pattern pattern;
    private FileIOService fileIOService;
    private String visitedName;
    private String updateMethodName;
    private String changeStateMethodName;
    private String stateName;
    private String stateType;
    private String visitedState;
    private String visitedStateObject;

    private String makeVisitorTemplate(String fileName) throws IOException {
        return fileIOService.ReadFromFile("patterns/visitor/" + fileName + ".txt");
    }

    private String HandleComments(String code) throws IOException {

        if(!this.pattern.getAreCommentsIncluded()) {
            code = code.replaceAll("%creationInfo%", "");
            code = code.replaceAll("%crudInfo%", "");
            code = code.replaceAll("%informInfo%", "");
            code = code.replaceAll("%updateInfo%", "");
            code = code.replaceAll("%changeStateInfo%", "");
        }
        else {
            code = code.replaceAll("%creationInfo%", fileIOService.ReadFromFile("patterns/common/comments/creationDetails.txt"));
            code = code.replaceAll("%crudInfo%", fileIOService.ReadFromFile("patterns/visitor/comments/crudInfo.txt"));
            code = code.replaceAll("%informInfo%", fileIOService.ReadFromFile("patterns/visitor/comments/informInfo.txt"));
            code = code.replaceAll("%updateInfo%", fileIOService.ReadFromFile("patterns/visitor/comments/updateInfo.txt"));
            code = code.replaceAll("%changeStateInfo%", fileIOService.ReadFromFile("patterns/visitor/comments/changeStateInfo.txt"));
        }
        return code;
    }


    @Autowired
    public VisitorGenerator(FileIOService fileIOService) {
        this.fileIOService = fileIOService;
    }

    @Override
    public String GenerateVisitorInterface() throws IOException {
        String observatorString = this.makeVisitorTemplate("visitorInterface");
        observatorString = observatorString.replaceAll("%visitor%", this.pattern.getName());
        observatorString = observatorString.replaceAll("%update%", this.updateMethodName);
        observatorString = HandleComments(observatorString);
        return observatorString;
    }

    @Override
    public String GenerateVisitedInterface() throws IOException {
        String visitorString = this.makeVisitorTemplate("visitedInterface");
        visitorString = visitorString.replaceAll("%observated%", this.visitedName);
        visitorString = visitorString.replaceAll("%observator%", this.pattern.getName());
        visitorString = HandleComments(visitorString);
        return visitorString;
    }

    @Override
    public String GenerateVisitorClass() throws IOException {
        String visitorString = this.makeVisitorTemplate("visitorClass");
        visitorString = visitorString.replaceAll("%visited%", this.visitedName);
        visitorString = visitorString.replaceAll("%visitor%", this.pattern.getName());
        visitorString = visitorString.replaceAll("%update%", this.updateMethodName);
        visitorString = visitorString.replaceAll("%changeState%", this.changeStateMethodName);
        visitorString = visitorString.replaceAll("%stateName%", this.stateName);
        visitorString = visitorString.replaceAll("%stateType%", this.stateType);
        visitorString = visitorString.replaceAll("%visitedState%", this.visitedState);
        visitorString = visitorString.replaceAll("%visitedStateObject%", this.visitedStateObject);
        visitorString = HandleComments(visitorString);
        return visitorString;
    }

    @Override
    public String GenerateVisitedClass() throws IOException {
        String visitorString = this.makeVisitorTemplate("visitedClass");
        visitorString = visitorString.replaceAll("%visited%", this.visitedName);
        visitorString = visitorString.replaceAll("%visitor%", this.pattern.getName());
        visitorString = visitorString.replaceAll("%update%", this.updateMethodName);
        visitorString = visitorString.replaceAll("%visitedState%", this.visitedState);
        visitorString = visitorString.replaceAll("%visitedStateObject%", this.visitedStateObject);
        visitorString = HandleComments(visitorString);
        return visitorString;
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

    public String getVisitedName() {
        return visitedName;
    }

    public void setVisitedName(String visitedName) {
        this.visitedName = visitedName;
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

    public String getVisitedState() {
        return visitedState;
    }

    public void setVisitedState(String visitedState) {
        this.visitedState = visitedState;
    }

    public String getVisitedStateObject() {
        return visitedStateObject;
    }

    public void setVisitedStateObject(String visitedStateObject) {
        this.visitedStateObject = visitedStateObject;
    }
}

