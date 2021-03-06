package PatternsGenerator.controllers.generatorControllers;

import PatternsGenerator.model.classes.common.Pattern;
import PatternsGenerator.model.classes.patternGenerator.PrototypeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class Prototypecontroller {

    @Autowired
    private Pattern pattern;
    @Autowired
    private PrototypeGenerator prototypeGenerator;

    private void CreatePrototype() {
        pattern.setAreCommentsIncluded(true);
        pattern.setName("PrototypeExample");
        prototypeGenerator.setPattern(pattern);
        prototypeGenerator.setClientName("PrototypeClientExample");
        prototypeGenerator.setInstanceName("instance");
        prototypeGenerator.setCopyMethodName("MakePrototypeCopy");
    }

    private void SendPrototypeToModel(Model model) throws IOException {
        model.addAttribute("prototype", this.prototypeGenerator.GeneratePrototypeClass());
        model.addAttribute("client", this.prototypeGenerator.GenerateClientClass());

        model.addAttribute("prototypeName", this.prototypeGenerator.getPattern().getName());
        model.addAttribute("clientName", this.prototypeGenerator.getClientName());
        model.addAttribute("instanceName", this.prototypeGenerator.getInstanceName());
        model.addAttribute("copyMethodName", this.prototypeGenerator.getCopyMethodName());
        model.addAttribute("areCommentsIncluded", this.prototypeGenerator.getPattern().getAreCommentsIncluded());
    }

    @GetMapping("/prototype")
    public String ShowPrototypPage(Model model) throws IOException {
        this.CreatePrototype();
        this.SendPrototypeToModel(model);
        return "prototype";
    }

    @PostMapping("/prototype")
    public void GenerateCode(Model model,
                             @RequestParam String prototypeName,
                             @RequestParam String clientName,
                             @RequestParam String instanceName,
                             @RequestParam String copyMethodName,
                             @RequestParam(defaultValue="false") boolean areCommentsIncluded)
            throws IOException {
        pattern.setAreCommentsIncluded(areCommentsIncluded);
        pattern.setName(prototypeName);
        prototypeGenerator.setPattern(pattern);
        prototypeGenerator.setClientName(clientName);
        prototypeGenerator.setInstanceName(instanceName);
        prototypeGenerator.setCopyMethodName(copyMethodName);

        this.SendPrototypeToModel(model);
 }


}
