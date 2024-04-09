package akatsuki.moodholic.controller;

import akatsuki.moodholic.domain.*;
import akatsuki.moodholic.dto.ResponseDiary;
import akatsuki.moodholic.repository.DiaryEmotionDAO;
import akatsuki.moodholic.repository.DiaryFoodDAO;
import akatsuki.moodholic.repository.DiaryMovieDAO;
import akatsuki.moodholic.repository.DiaryMusicDAO;
import akatsuki.moodholic.service.*;
import io.swagger.v3.oas.annotations.Operation;
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
    public DiaryController(DiaryService diaryService, DiaryFoodService diaryFoodService, DiaryEmotionService diaryEmotionService, DiaryMovieService diaryMovieService, DiaryMusicService diaryMusicService, CommentService commentService) {
        this.diaryService = diaryService;

    }

    @GetMapping("/{diaryId}")
    @Operation(summary = "다이어리 상세 조회", description = "한 다이어리의 정보와 GPT의 응답을 반환합니다.")
    public ResponseEntity<ResponseDiary> getDiary(@PathVariable int diaryId){
        ResponseDiary response = diaryService.getDiary(diaryId);

        return ResponseEntity.ok().body(response);
    }
    @PostMapping("")
    @Operation(summary = "다이어리 저장", description = "요청온 다이어리의 상태에 따라 임시저장 또는 저장을 수행합니다. " +
            "저장 시 ChatGPT에 프롬프트 전달하여 GPT의 응답을 받아 DB에 저장합니다.")
    public ResponseEntity<String> postDiary(@RequestBody Diary diary){
        String response = diaryService.postDiary(diary);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("{diaryId}")
    @Operation(summary = "다이어리 삭제", description = "선택한 다이어리와 다이어리와 연관된 모든 객체들을 삭제합니다.")
    public ResponseEntity<String> deleteDiary(@PathVariable int diaryId){
        String response = diaryService.deleteDiary(diaryId);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{memberid}/diaries")
    @Operation(summary = "맴버의 다이어리 리스트 조회", description = "멤버가 작성한 모든 다이어리를 반환합니다.")
    public ResponseEntity<List<Diary> > getMemberDiaries(@PathVariable long memberid){
        List<Diary> response = diaryService.getMemberDiaries(memberid);
        return ResponseEntity.ok().body(response);
    }

}
