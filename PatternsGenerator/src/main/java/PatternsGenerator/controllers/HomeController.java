package PatternsGenerator.controllers;

import PatternsGenerator.model.classes.common.Attribute;
import PatternsGenerator.model.classes.common.Pattern;
import PatternsGenerator.model.classes.singleton.SingletonGenerator;
import PatternsGenerator.services.FileIOService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;


@Controller
public class HomeController {

    @GetMapping("/home")
    public String ShowHomePage(Model model) throws IOException {
        Pattern pattern = new Pattern();
        pattern.setName("testSingleton");
        SingletonGenerator singletonGenerator = new SingletonGenerator(new FileIOService());
        singletonGenerator.setPattern(pattern);
        singletonGenerator.getAttributes().add(new Attribute(new FileIOService(), "String", "attr1"));
        singletonGenerator.getAttributes().add(new Attribute(new FileIOService(), "String", "attr2"));
        singletonGenerator.getAttributes().add(new Attribute(new FileIOService(), "int", "attr3"));
        String test = singletonGenerator.ToString();
        model.addAttribute("test", test);
        return "home";
    }
}