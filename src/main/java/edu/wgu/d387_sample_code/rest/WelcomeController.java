package edu.wgu.d387_sample_code.rest;

import edu.wgu.d387_sample_code.convertor.WelcomeService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class WelcomeController {

    private final WelcomeService welcomeService;

    public WelcomeController(WelcomeService welcomeService) {
        this.welcomeService = welcomeService;
    }

    @GetMapping("/welcome")
    public Map<String, String> getWelcomeMessages() {
        return welcomeService.loadWelcomeMessage();
    }
}
