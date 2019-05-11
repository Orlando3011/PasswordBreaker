package PatternsGenerator.controllers.generatorControllers;

import PatternsGenerator.model.classes.common.Pattern;
import PatternsGenerator.model.classes.patternGenerator.SingletonGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class SingletonController {

    @Autowired
    private Pattern pattern;
    @Autowired
    private SingletonGenerator singletonGenerator;

    @GetMapping("/singleton")
    public String ShowSingletonPage(Model model) throws IOException {
        return "singleton";
    }

    @PostMapping("/singleton")
    public String generateCode(Model model,
                               @RequestParam String singletonType,
                               @RequestParam String className,
                               @RequestParam(defaultValue="false") boolean areCommentsIncluded)
            throws IOException {
        switch(singletonType) {
            case "Simple (not thread-safe)": {
                pattern.setVersion(1);
                break;
            }
            case "Lazy (not thread-safe)": {
                pattern.setVersion(2);
                break;
            }
            case "Advanced (thread safe - requires Java 5 or newer)": {
                pattern.setVersion(3);
                break;
            }
        }
        pattern.setAreCommentsIncluded(areCommentsIncluded);
        pattern.setName(className);
        singletonGenerator.setPattern(pattern);
        model.addAttribute("code", singletonGenerator.GenerateSingletonClass());
        return "singleton";
    }
}
