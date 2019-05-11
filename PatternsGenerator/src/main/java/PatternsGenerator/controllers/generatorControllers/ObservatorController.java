package PatternsGenerator.controllers.generatorControllers;

import PatternsGenerator.model.classes.common.Pattern;
import PatternsGenerator.model.classes.patternGenerator.ObservatorGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class ObservatorController {

    @Autowired
    private Pattern pattern;
    @Autowired
    private ObservatorGenerator observatorGenerator;

    @GetMapping("/observator")
    public String ShowSingletonPage(Model model) throws IOException {
        return "observator";
    }

    @PostMapping("/observator")
    public String generateCode(Model model,
                               @RequestParam String className,
                               @RequestParam String observatedName,
                               @RequestParam String updateMethodName,
                               @RequestParam String changeStateMethodName,
                               @RequestParam String stateName,
                               @RequestParam String stateType,
                               @RequestParam String observatedState,
                               @RequestParam String observatedStateObject,
                               @RequestParam(defaultValue="false") boolean areCommentsIncluded)
            throws IOException {
        pattern.setAreCommentsIncluded(areCommentsIncluded);
        pattern.setName(className);
        observatorGenerator.setPattern(pattern);
        observatorGenerator.setObservatedName(observatedName);
        observatorGenerator.setUpdateMethodName(updateMethodName);
        observatorGenerator.setChangeStateMethodName(changeStateMethodName);
        observatorGenerator.setStateName(stateName);
        observatorGenerator.setStateType(stateType);
        observatorGenerator.setObservatedState(observatedState);
        observatorGenerator.setObservatedStateObject(observatedStateObject);
        model.addAttribute("observatorInterfaceCode", observatorGenerator.GenerateObservatorInterface());
        model.addAttribute("observatedInterfaceCode", observatorGenerator.GenerateObservatedInterface());
        model.addAttribute("observatorClassCode", observatorGenerator.GenerateObservatorClass());
        model.addAttribute("observatedClassCode", observatorGenerator.GenerateObservatedClass());

        return "observator";
    }


}
