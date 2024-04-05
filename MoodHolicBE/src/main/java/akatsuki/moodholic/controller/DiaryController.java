package akatsuki.moodholic.controller;

import akatsuki.moodholic.domain.Diary;
import akatsuki.moodholic.domain.DiaryMusic;
import akatsuki.moodholic.dto.ResponseDiary;
import akatsuki.moodholic.repository.DiaryEmotionDAO;
import akatsuki.moodholic.repository.DiaryFoodDAO;
import akatsuki.moodholic.repository.DiaryMovieDAO;
import akatsuki.moodholic.repository.DiaryMusicDAO;
import akatsuki.moodholic.service.DiaryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/diary")
@Tag(name = "다이어리 컨트롤러", description = "맴버의 다이어리 리스트, 상세 리스트")
public class DiaryController {
    DiaryService diaryService;

    @Autowired
    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    // 다이어리 상세 조회   -fin
    @GetMapping("/{diaryId}")
    public ResponseEntity<ResponseDiary> getDiary(@PathVariable int diaryId){
        ResponseDiary responseDiary = diaryService.getDiary(diaryId);

        return ResponseEntity.ok().body(responseDiary);
    }
    // 다이어리 저장  -fin
    @PostMapping("")
    public ResponseEntity<String> postDiary(@RequestBody Diary diary){
        String response = diaryService.postDiary(diary);
        return ResponseEntity.ok().body(response);
    }

    // 다이어리 삭제  -fin
    @DeleteMapping("{diaryId}")
    public ResponseEntity<String> deleteDiary(@PathVariable int diaryId){
        String response = diaryService.deleteDiary(diaryId);
        return ResponseEntity.ok().body(response);
    }

    //맴버의 다이어리 리스트  -fin
    @GetMapping("/{memberid}/diaries")
    public ResponseEntity<List<Diary> > getMemberDiaries(@PathVariable long memberid){
        List<Diary> response = diaryService.getMemberDiaries(memberid);
        return ResponseEntity.ok().body(response);
    }

}
