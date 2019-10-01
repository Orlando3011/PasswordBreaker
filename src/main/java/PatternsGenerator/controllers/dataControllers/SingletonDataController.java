package PatternsGenerator.controllers.dataControllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SingletonDataController {

    @GetMapping("/singletonData")
    public String ShowSingletonDataPage() {
        return "singletonData";
    }
}
