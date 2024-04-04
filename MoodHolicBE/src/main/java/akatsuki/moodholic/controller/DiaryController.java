package akatsuki.moodholic.controller;

import akatsuki.moodholic.domain.Diary;
import akatsuki.moodholic.domain.DiaryMusic;
import akatsuki.moodholic.dto.ResponseDiary;
import akatsuki.moodholic.repository.DiaryEmotionDAO;
import akatsuki.moodholic.repository.DiaryFoodDAO;
import akatsuki.moodholic.repository.DiaryMovieDAO;
import akatsuki.moodholic.repository.DiaryMusicDAO;
import akatsuki.moodholic.service.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/diary")
public class DiaryController {
    DiaryService diaryService;


    @Autowired
    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    // 다이어리 상세 조회
    @GetMapping("/{diaryId}")
    public ResponseEntity<ResponseDiary> getDiary(@PathVariable int diaryId){
        ResponseDiary responseDiary = diaryService.getDiary(diaryId);

        return ResponseEntity.ok().body(responseDiary);
    }
    // 다이어리 저장
    @PostMapping("")
    public ResponseEntity postDiary(@RequestBody Diary diary){
        diaryService.postDiary(diary);
        return ResponseEntity.ok().build();
    }

    // 다이어리 삭제
    @DeleteMapping("")
    public ResponseEntity deleteDiary(){
        return null;
    }

    //맴버의 다이어리 리스트
    @GetMapping("/{memberid}/diaries")
    public ResponseEntity<List<Diary> > getMemberDiaries(){
        return null;
    }






}
