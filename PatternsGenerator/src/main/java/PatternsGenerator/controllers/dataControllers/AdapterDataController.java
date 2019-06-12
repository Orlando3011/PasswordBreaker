package PatternsGenerator.controllers.dataControllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdapterDataController {

    @GetMapping("/adapterData")
    public String ShowFactoryDataPage() {
        return "adapterData";
    }
}
