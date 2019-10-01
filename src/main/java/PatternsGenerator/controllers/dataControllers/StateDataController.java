package PatternsGenerator.controllers.dataControllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class StateDataController {

    @GetMapping("/stateData")
    public String ShowStateDataPage()  {
        return "stateData";
    }
}