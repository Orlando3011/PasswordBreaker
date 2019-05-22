package PatternsGenerator.controllers.dataControllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class VisitorDataController {

    @GetMapping("/visitorData")
    public String ShowVisitorDataPage(Model model) throws IOException {
        return "visitorData";
    }
}
