package PatternsGenerator.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;


@Controller
public class HomeController {

    @GetMapping("/home")
    public String ShowHomePage(Model model) throws IOException {
        return "home";
    }
}