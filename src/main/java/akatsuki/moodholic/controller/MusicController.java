package akatsuki.moodholic.controller;

import akatsuki.moodholic.domain.Music;
import akatsuki.moodholic.service.MusicService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category/music")
@Tag(name = "노래 조회 컨트롤러", description = "추천받은 노래를 조회하는 기능")
public class MusicController {

    private final MusicService musicService;

    @Autowired
    public MusicController(MusicService musicService) {
        this.musicService = musicService;
    }

    @GetMapping("/all")
    @Operation(summary = "음식 전체 조회", description = "단순 음식 조회 기능")
    public List<Music> getAllFoods() {
        return musicService.getAllMusics();
    }
}
