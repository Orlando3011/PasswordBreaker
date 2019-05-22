package PatternsGenerator.controllers.generatorControllers;

import PatternsGenerator.model.classes.common.Pattern;
import PatternsGenerator.model.classes.patternGenerator.VisitorGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class VisitorController {

    @Autowired
    private Pattern pattern;
    @Autowired
    private VisitorGenerator visitorGenerator;

    @GetMapping("/visitor")
    public String ShowVisitorPage(Model model) throws IOException {
        return "visitor";
    }

    @PostMapping("/visitor")
    public String generateCode(Model model,
                               @RequestParam String className,
                               @RequestParam String visitedName,
                               @RequestParam String updateMethodName,
                               @RequestParam String changeStateMethodName,
                               @RequestParam String stateName,
                               @RequestParam String stateType,
                               @RequestParam String visitedState,
                               @RequestParam String visitedStateObject,
                               @RequestParam(defaultValue="false") boolean areCommentsIncluded)
            throws IOException {
        pattern.setAreCommentsIncluded(areCommentsIncluded);
        pattern.setName(className);
        visitorGenerator.setPattern(pattern);
        visitorGenerator.setVisitedName(visitedName);
        visitorGenerator.setUpdateMethodName(updateMethodName);
        visitorGenerator.setChangeStateMethodName(changeStateMethodName);
        visitorGenerator.setStateName(stateName);
        visitorGenerator.setStateType(stateType);
        visitorGenerator.setVisitedState(visitedState);
        visitorGenerator.setVisitedStateObject(visitedStateObject);
        model.addAttribute("visitorInterfaceCode", visitorGenerator.GenerateVisitorInterface());
        model.addAttribute("visitedInterfaceCode", visitorGenerator.GenerateVisitedInterface());
        model.addAttribute("visitorClassCode", visitorGenerator.GenerateVisitorClass());
        model.addAttribute("visitedClassCode", visitorGenerator.GenerateVisitedClass());

        return "visitor";
    }


}
