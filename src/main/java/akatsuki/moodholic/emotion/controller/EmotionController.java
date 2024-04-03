package akatsuki.moodholic.emotion.controller;

import akatsuki.moodholic.emotion.service.EmotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/emotion")
public class EmotionController {
    EmotionService emotionService;

    @Autowired
    public EmotionController(EmotionService emotionService) {
        this.emotionService = emotionService;
    }
}
