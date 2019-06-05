package PatternsGenerator.controllers.generatorControllers;

import PatternsGenerator.model.classes.common.Pattern;
import PatternsGenerator.model.classes.patternGenerator.FactoryGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class FactoryController {
    @Autowired
    private Pattern pattern;
    @Autowired
    private FactoryGenerator factoryGenerator;

    private void createFactory() {
        pattern.setAreCommentsIncluded(true);
        pattern.setName("FactoryExample");
        factoryGenerator.setPattern(pattern);
        factoryGenerator.setMainClassName("ProductExample");
        factoryGenerator.MenageSubclasses("Product1,Product2,Product3");
    }

    @GetMapping("/factory")
    public String ShowFactoryPage(Model model) throws IOException {
        this.createFactory();
        model.addAttribute("factoryName", this.pattern.getName());
        model.addAttribute("interfaceName", this.factoryGenerator.getMainClassName());
        model.addAttribute("subclassesExample", this.factoryGenerator.SubclassesToStrings());
        model.addAttribute("areCommentsIncluded", this.pattern.getAreCommentsIncluded());

        model.addAttribute("factory", this.factoryGenerator.GenerateFactoryInterface());
        model.addAttribute("interface", this.factoryGenerator.GenerateInterface());
        model.addAttribute("subfactories", this.factoryGenerator.GenerateSubfactoryClass());
        model.addAttribute("subclasses", this.factoryGenerator.GenerateSubclass());
        return "factory";
    }

    @PostMapping("/factory")
    public void GenerateCode(Model model,
                               @RequestParam String factoryName,
                               @RequestParam String interfaceName,
                               @RequestParam String subclasses,
                               @RequestParam(defaultValue="false") boolean areCommentsIncluded)
        throws IOException {
        pattern.setAreCommentsIncluded(areCommentsIncluded);
        pattern.setName(factoryName);
        factoryGenerator.setPattern(pattern);
        factoryGenerator.setMainClassName(interfaceName);
        factoryGenerator.MenageSubclasses(subclasses);

        model.addAttribute("factory", this.factoryGenerator.GenerateFactoryInterface());
        model.addAttribute("interface", this.factoryGenerator.GenerateInterface());
        model.addAttribute("subfactories", this.factoryGenerator.GenerateSubfactoryClass());
        model.addAttribute("subclasses", this.factoryGenerator.GenerateSubclass());

        model.addAttribute("factoryName", this.pattern.getName());
        model.addAttribute("interfaceName", this.factoryGenerator.getMainClassName());
        model.addAttribute("subclassesExample", this.factoryGenerator.SubclassesToStrings());
        model.addAttribute("areCommentsIncluded", this.pattern.getAreCommentsIncluded());
    }
}