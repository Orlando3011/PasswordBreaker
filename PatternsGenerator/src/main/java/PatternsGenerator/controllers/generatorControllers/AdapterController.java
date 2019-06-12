package PatternsGenerator.controllers.generatorControllers;

import PatternsGenerator.model.classes.common.Pattern;
import PatternsGenerator.model.classes.patternGenerator.AdapterGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class AdapterController {
    @Autowired
    private Pattern pattern;
    @Autowired
    private AdapterGenerator adapterGenerator;

    private void createAdapter() {
        pattern.setAreCommentsIncluded(true);
        pattern.setName("AdapterExample");
        adapterGenerator.setPattern(pattern);
        adapterGenerator.setFirstClassName("ExampleClass1");
        adapterGenerator.setSecondClassName("ExampleClass2");
        adapterGenerator.setMethodName("DoSomething");
        adapterGenerator.setMethodParameters("String exampleParameter");
        adapterGenerator.setMethodReturn("int");
    }

    @GetMapping("/adapter")
    public String ShowAdapterPage(Model model) throws IOException {
        this.createAdapter();

        model.addAttribute("interface", adapterGenerator.GenerateInterface());
        model.addAttribute("firstClass", adapterGenerator.GenerateFirstClass());
        model.addAttribute("secondClass", adapterGenerator.GenerateSecondClass());
        model.addAttribute("firstAdapter", adapterGenerator.GenerateFirstAdapter());
        model.addAttribute("secondAdapter", adapterGenerator.GenerateSecondAdapter());

        model.addAttribute("adapterName", this.pattern.getName());
        model.addAttribute("firstClassName", this.adapterGenerator.getFirstClassName());
        model.addAttribute("secondClassName", this.adapterGenerator.getSecondClassName());
        model.addAttribute("methodName", this.adapterGenerator.getMethodName());
        model.addAttribute("methodParameters", this.adapterGenerator.getMethodParameters());
        model.addAttribute("methodReturn", this.adapterGenerator.getMethodReturn());
        model.addAttribute("areCommentsIncluded", this.pattern.getAreCommentsIncluded());

        return "adapter";
    }

    @PostMapping("/adapter")
    public void GenerateCode(Model model,
                             @RequestParam String adapterName,
                             @RequestParam String firstClassName,
                             @RequestParam String secondClassName,
                             @RequestParam String methodName,
                             @RequestParam String methodParameters,
                             @RequestParam String methodReturn,
                             @RequestParam(defaultValue="false") boolean areCommentsIncluded)
            throws IOException {
        pattern.setAreCommentsIncluded(areCommentsIncluded);
        pattern.setName(adapterName);
        adapterGenerator.setPattern(pattern);
        adapterGenerator.setFirstClassName(firstClassName);
        adapterGenerator.setSecondClassName(secondClassName);
        adapterGenerator.setMethodName(methodName);
        adapterGenerator.setMethodParameters(methodParameters);
        adapterGenerator.setMethodReturn(methodReturn);

        model.addAttribute("interface", adapterGenerator.GenerateInterface());
        model.addAttribute("firstClass", adapterGenerator.GenerateFirstClass());
        model.addAttribute("secondClass", adapterGenerator.GenerateSecondClass());
        model.addAttribute("firstAdapter", adapterGenerator.GenerateFirstAdapter());
        model.addAttribute("secondAdapter", adapterGenerator.GenerateSecondAdapter());

        model.addAttribute("adapterName", this.pattern.getName());
        model.addAttribute("firstClassName", this.adapterGenerator.getFirstClassName());
        model.addAttribute("secondClassName", this.adapterGenerator.getSecondClassName());
        model.addAttribute("methodName", this.adapterGenerator.getMethodName());
        model.addAttribute("methodParameters", this.adapterGenerator.getMethodParameters());
        model.addAttribute("methodReturn", this.adapterGenerator.getMethodReturn());
        model.addAttribute("areCommentsIncluded", this.pattern.getAreCommentsIncluded());
    }
}
