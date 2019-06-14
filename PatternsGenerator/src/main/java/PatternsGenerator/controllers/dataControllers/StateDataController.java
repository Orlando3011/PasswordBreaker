package PatternsGenerator.controllers.dataControllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class StateDataController {

    @GetMapping("/stateData")
    public String ShowStateDataPage(Model model) throws IOException {
        return "stateData";
    }
}