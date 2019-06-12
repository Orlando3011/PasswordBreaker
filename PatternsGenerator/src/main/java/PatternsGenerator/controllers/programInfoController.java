package PatternsGenerator.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class programInfoController {

    @GetMapping("/programInfo")
    public String ShowProgramInfoPage() {
        return "programInfo";
    }
}
