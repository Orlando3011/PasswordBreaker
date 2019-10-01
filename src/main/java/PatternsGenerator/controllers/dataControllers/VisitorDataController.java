package PatternsGenerator.controllers.dataControllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VisitorDataController {

    @GetMapping("/visitorData")
    public String ShowVisitorDataPage() {
        return "visitorData";
    }
}
