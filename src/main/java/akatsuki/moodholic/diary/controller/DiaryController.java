package akatsuki.moodholic.diary.controller;

import akatsuki.moodholic.diary.service.DiaryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/diary")
public class DiaryController {
    DiaryService diaryService;

    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    //@GetMapping("/{diaryId}")


}
