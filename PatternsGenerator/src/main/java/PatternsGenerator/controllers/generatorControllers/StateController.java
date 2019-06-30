package PatternsGenerator.controllers.generatorControllers;

import PatternsGenerator.model.classes.common.Pattern;
import PatternsGenerator.model.classes.patternGenerator.StateGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class StateController {
    @Autowired
    private Pattern pattern;
    @Autowired
    private StateGenerator stateGenerator;

    private void CreateState() {
        pattern.setAreCommentsIncluded(true);
        pattern.setName("StateExample");
        stateGenerator.setPattern(pattern);
        stateGenerator.setChangeStateMethiod("ChangeState");
        stateGenerator.setClassName("ClassWithState");
        stateGenerator.MenageSubclasses("FirstState,SecondState,ThirdState");
    }

    private void SendStateToModel(Model model) throws IOException {
        model.addAttribute("stateName", pattern.getName());
        model.addAttribute("methodName", stateGenerator.getChangeStateMethiod());
        model.addAttribute("className", stateGenerator.getClassName());
        model.addAttribute("subclasses", stateGenerator.SubclassesToStrings());
        model.addAttribute("areCommentsIncluded", pattern.getAreCommentsIncluded());

        model.addAttribute("state", stateGenerator.GenerateState());
        model.addAttribute("class", stateGenerator.GenerateClassWithState());
        model.addAttribute("stateSubclasses", stateGenerator.GenerateStateSubclasses());
    }

    @GetMapping("/state")
    public String ShowStateage(Model model) throws IOException {
        this.CreateState();
        this.SendStateToModel(model);
        return "state";
    }

    @PostMapping("/state")
    public void GenerateCode(Model model,
                             @RequestParam String stateName,
                             @RequestParam String methodName,
                             @RequestParam String className,
                             @RequestParam String subclasses,
                             @RequestParam(defaultValue="false") boolean areCommentsIncluded)
            throws IOException {
        pattern.setAreCommentsIncluded(areCommentsIncluded);
        pattern.setName(stateName);
        stateGenerator.setPattern(pattern);
        stateGenerator.setChangeStateMethiod(methodName);
        stateGenerator.setClassName(className);
        stateGenerator.MenageSubclasses(subclasses);

        this.SendStateToModel(model);
    }
}
