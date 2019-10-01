package PatternsGenerator.controllers.dataControllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CompositeDataController {

    @GetMapping("/compositeData")
    public String ShowCompositeDataPage() {
        return "compositeData";
    }
}
