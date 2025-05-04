package edu.wgu.d387_sample_code.rest;

import edu.wgu.d387_sample_code.convertor.WelcomeService;
import edu.wgu.d387_sample_code.model.response.WelcomeResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class WelcomeController {
    private final WelcomeService welcomeService;

    public WelcomeController(WelcomeService welcomeService) {
        this.welcomeService = welcomeService;
    }

    @GetMapping("/welcome")
    public ResponseEntity<WelcomeResponse> getWelcomeMessages(){
        WelcomeResponse response = welcomeService.loadWelcomeMessage();
        return ResponseEntity.ok(response);
    }

}
