package akatsuki.moodholic.controller;

import akatsuki.moodholic.domain.DiaryMovie;
import akatsuki.moodholic.domain.Movie;
import akatsuki.moodholic.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category/movie")
@Tag(name = "영화 조회 컨트롤러", description = "추천받은 영화를 조회하는 기능")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/all")
    @Operation(summary = "영화 전체 조회", description = "단순 영화 조회 기능")
    public List<Movie> getAllFoods() {
        return movieService.getAllMovies();
    }

    @GetMapping("/liked")
    @Operation(summary = "좋아요 표시된 영화 조회", description = "사용자가 좋아요 표시한 영화 조회 기능")
    public List<DiaryMovie> getLikedDiaryMovies() {
        return movieService.findLikedDiaryMovies();
    }

    @GetMapping("/liked/names")
    public List<String> getLikedMovieNames() {
        return movieService.findLikedMovieNames();
    }

    @GetMapping("/genres/likes-count")
    public List<Object[]> countMovieGenresWithLikes() {
        return movieService.countMovieGenresWithLikes();
    }
}
