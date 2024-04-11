package akatsuki.moodholic.service.facade;

import akatsuki.moodholic.domain.Diary;
import akatsuki.moodholic.domain.DiaryMovie;
import akatsuki.moodholic.domain.Movie;
import akatsuki.moodholic.service.DiaryMovieService;
import akatsuki.moodholic.service.DiaryService;
import akatsuki.moodholic.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieFacadeService {
    MovieService movieService;
    DiaryMovieService diaryMovieService;
    DiaryService diaryService;

    @Autowired
    public MovieFacadeService(MovieService movieService, DiaryMovieService diaryMovieService, DiaryService diaryService) {
        this.movieService = movieService;
        this.diaryMovieService = diaryMovieService;
        this.diaryService = diaryService;
    }

    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    public List<DiaryMovie> findLikedDiaryMovies() {
        return diaryMovieService.findLikedDiaryMovies();
    }
    public List<String> findLikedMovieNames() {
        return diaryMovieService.findLikedMovieNames();
    }

    public List<Object[]> countMovieGenresWithLikes() {
        return movieService.countMovieGenresWithLikes();
    }

    public List<DiaryMovie> getMemberLikedMovie(long memberId) {
        List<Diary> diaries = diaryService.getMemberDiaries(memberId);
        return diaryMovieService.getMemberLikedMovie(diaries);
    }

}
