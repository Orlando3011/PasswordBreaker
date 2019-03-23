package PatternsGenerator.controllers;

import PatternsGenerator.model.classes.Attribute;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.io.IOException;


@Controller
public class HomeController {

    @GetMapping("/home")
    public String ShowHomePage(Model model) throws IOException {

        Attribute attribute = new Attribute();
        String test = attribute.ToString();
        model.addAttribute("test", test);
        return "home";
    }
}
