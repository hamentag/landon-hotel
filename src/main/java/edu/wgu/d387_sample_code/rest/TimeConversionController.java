package edu.wgu.d387_sample_code.rest;

import edu.wgu.d387_sample_code.convertor.TimeConversionService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class TimeConversionController {

    private final TimeConversionService timeConversionService;

    public TimeConversionController(TimeConversionService timeConversionService) {
        this.timeConversionService = timeConversionService;
    }

    @GetMapping("/converted-times")
    public Map<String, String> getConvertedTimes() {
        return timeConversionService.getConvertedTimes();
    }
}
