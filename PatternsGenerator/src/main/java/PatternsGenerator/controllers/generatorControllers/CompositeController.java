package PatternsGenerator.controllers.generatorControllers;

import PatternsGenerator.model.classes.common.Pattern;
import PatternsGenerator.model.classes.patternGenerator.CompositeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class CompositeController {
    @Autowired
    private Pattern pattern;
    @Autowired
    private CompositeGenerator compositeGenerator;

    private void createComposite() {
        pattern.setAreCommentsIncluded(true);
        pattern.setName("CompositeExample");
        compositeGenerator.setPattern(pattern);
        compositeGenerator.setInterfaceName("ExampleInterface");
        compositeGenerator.MenageSubclasses("Class1,Class2,Class3");
    }


    @GetMapping("/composite")
    public String ShowCompositePage(Model model) throws IOException {
        this.createComposite();
        model.addAttribute("compositeName", pattern.getName());
        model.addAttribute("interfaceName", compositeGenerator.getInterfaceName());
        model.addAttribute("subclasses", compositeGenerator.SubclassesToStrings());
        model.addAttribute("areCommentsIncluded", pattern.getAreCommentsIncluded());

        model.addAttribute("composite", compositeGenerator.GenerateComposite());
        model.addAttribute("interface", compositeGenerator.GenerateInterface());
        model.addAttribute("subclassesCode", compositeGenerator.GenerateSubclasses());
        return "composite";
    }

    @PostMapping("/composite")
    public void GenerateCode(Model model,
                             @RequestParam String compositeName,
                             @RequestParam String interfaceName,
                             @RequestParam String subclasses,
                             @RequestParam(defaultValue="false") boolean areCommentsIncluded)
            throws IOException {

        pattern.setAreCommentsIncluded(areCommentsIncluded);
        pattern.setName(compositeName);
        compositeGenerator.setPattern(pattern);
        compositeGenerator.setInterfaceName(interfaceName);
        compositeGenerator.MenageSubclasses(subclasses);

        model.addAttribute("compositeName", pattern.getName());
        model.addAttribute("interfaceName", compositeGenerator.getInterfaceName());
        model.addAttribute("subclasses", compositeGenerator.SubclassesToStrings());
        model.addAttribute("areCommentsIncluded", pattern.getAreCommentsIncluded());

        model.addAttribute("composite", compositeGenerator.GenerateComposite());
        model.addAttribute("interface", compositeGenerator.GenerateInterface());
        model.addAttribute("subclassesCode", compositeGenerator.GenerateSubclasses());
    }
}

