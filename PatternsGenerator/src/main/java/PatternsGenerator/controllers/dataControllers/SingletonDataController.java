package PatternsGenerator.controllers.dataControllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class SingletonDataController {

    @GetMapping("/singletonData")
    public String ShowSingletonDataPage(Model model) throws IOException {
        return "singletonData";
    }
}
