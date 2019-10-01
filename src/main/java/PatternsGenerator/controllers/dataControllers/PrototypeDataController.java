package PatternsGenerator.controllers.dataControllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PrototypeDataController {

    @GetMapping("/prototypeData")
    public String ShowPrototypeDataPage() {
        return "prototypeData";
    }
}
