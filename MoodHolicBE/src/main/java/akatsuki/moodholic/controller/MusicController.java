package akatsuki.moodholic.controller;

import akatsuki.moodholic.domain.DiaryMusic;
import akatsuki.moodholic.domain.Music;
import akatsuki.moodholic.service.MusicService;
import akatsuki.moodholic.service.facade.MusicFacadeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category/music")
@Tag(name = "노래 조회 컨트롤러", description = "추천받은 노래를 조회하는 기능")
public class MusicController {

    private final MusicFacadeService musicFacadeService;

    @Autowired
    public MusicController(MusicFacadeService musicFacadeService) {
        this.musicFacadeService = musicFacadeService;
    }

    @GetMapping("/all")
    @Operation(summary = "노래 전체 조회", description = "단순 노래 조회 기능")
    public ResponseEntity<List<Music>> getAllFoods() {
        return ResponseEntity.ok().body(musicFacadeService.getAllMusics());
    }

    @GetMapping("/liked")
    @Operation(summary = "좋아요 표시된 노래 조회", description = "사용자가 좋아요 표시한 노래 조회 기능")
    public ResponseEntity<List<DiaryMusic>> getLikedDiaryMusics() {
        return ResponseEntity.ok().body(musicFacadeService.findLikedDiaryMusics());
    }

    @GetMapping("/liked/names")
    public ResponseEntity<List<String>> getLikedMusicNames() {
        return ResponseEntity.ok().body(musicFacadeService.findLikedMusicNames());
    }

    @GetMapping("/genres/likes-count")
    public ResponseEntity<List<Object[]>> countMusicGenresWithLikes() {
        return ResponseEntity.ok().body(musicFacadeService.countMusicGenresWithLikes());
    }

    @GetMapping("/liked/{memberId}")
    public ResponseEntity<List<DiaryMusic>> getMemberLikeMusic(@PathVariable long memberId){
        return ResponseEntity.ok().body(musicFacadeService.getMemberLikeMusic(memberId));
    }

}
