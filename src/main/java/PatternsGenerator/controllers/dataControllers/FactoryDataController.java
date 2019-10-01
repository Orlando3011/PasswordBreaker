package PatternsGenerator.controllers.dataControllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FactoryDataController {

    @GetMapping("/factoryData")
    public String ShowFactoryDataPage() {
        return "factoryData";
    }
}
