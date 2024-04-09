package akatsuki.moodholic.controller;

import akatsuki.moodholic.domain.DiaryMovie;
import akatsuki.moodholic.domain.Movie;
import akatsuki.moodholic.service.MovieService;
import akatsuki.moodholic.service.facade.MovieFacadeService;
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
@RequestMapping("/category/movie")
@Tag(name = "영화 조회 컨트롤러", description = "추천받은 영화를 조회하는 기능")
public class MovieController {

    MovieFacadeService movieFacadeService;

    @Autowired
    public MovieController(MovieFacadeService movieFacadeService) {
        this.movieFacadeService = movieFacadeService;
    }

    @GetMapping("/all")
    @Operation(summary = "영화 전체 조회", description = "단순 영화 조회 기능")
    public ResponseEntity<List<Movie>> getAllFoods() {
        return ResponseEntity.ok().body(movieFacadeService.getAllMovies());
    }

    @GetMapping("/liked")
    @Operation(summary = "좋아요 표시된 영화 조회", description = "사용자가 좋아요 표시한 영화 조회 기능")
    public ResponseEntity<List<DiaryMovie>> getLikedDiaryMovies() {
        return ResponseEntity.ok().body(movieFacadeService.findLikedDiaryMovies());
    }

    @GetMapping("/liked/names")
    public ResponseEntity<List<String>> getLikedMovieNames() {
        return ResponseEntity.ok().body(movieFacadeService.findLikedMovieNames());
    }

    @GetMapping("/genres/likes-count")
    public ResponseEntity<List<Object[]>> countMovieGenresWithLikes() {
        return ResponseEntity.ok().body(movieFacadeService.countMovieGenresWithLikes());
    }

    @GetMapping("/liked/{memberId}")
    public ResponseEntity<List<DiaryMovie>> getMemberLikedMovie(@PathVariable long memberId){
        return ResponseEntity.ok().body(movieFacadeService.getMemberLikedMovie(memberId));
    }
}
