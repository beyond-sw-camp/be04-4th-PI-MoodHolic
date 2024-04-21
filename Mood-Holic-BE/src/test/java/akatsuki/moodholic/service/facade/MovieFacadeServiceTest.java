package akatsuki.moodholic.service.facade;

import akatsuki.moodholic.domain.Diary;
import akatsuki.moodholic.domain.DiaryMovie;
import akatsuki.moodholic.domain.Movie;
import akatsuki.moodholic.dto.MemberMovieGenreRanking;
import akatsuki.moodholic.repository.DiaryMovieDAO;
import akatsuki.moodholic.repository.MovieDAO;
import akatsuki.moodholic.service.DiaryMovieService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MovieFacadeServiceTest {
    @Autowired
    MovieFacadeService movieFacadeService;
    @Autowired
    MovieDAO movieDAO;
    @Autowired
    DiaryMovieDAO diaryMovieDAO;
    @Autowired
    DiaryFacadeService diaryFacadeService;
    @Autowired
    DiaryMovieService diaryMovieService;
    @Test
    @DisplayName("모든 영화 불러오기")
    void getAllMovies() {
        List<Movie> movieList1 = movieFacadeService.getAllMovies();
        List<Movie> movieList2 = movieDAO.findAll();
        assertEquals(movieList1,movieList2);
    }

    @Test
    @DisplayName("멤버가 좋아요 표시한 영화 리스트")
    void getMemberLikedMovie() {
        long memberId = 1;
        List<DiaryMovie> diaryMovieList = movieFacadeService.getMemberLikedMovie(memberId);
        diaryMovieList.forEach(diaryMovie -> {
            assertEquals(true,diaryMovie.isMovieLove());
        });
    }

    @Test
    @DisplayName("멤버가 좋아요 한 영화 카테고리 랭킹")
    void getMemberMovieGenreRanking() {
        long memberId = 1;
        MemberMovieGenreRanking memberMovieGenreRanking = movieFacadeService.getMemberMovieGenreRanking(memberId);
        List<DiaryMovie> diaryMovieList = diaryMovieService.getMemberLikedMovie(memberId);
        diaryMovieList.forEach(diaryMovie -> {
            if(diaryMovie.getMovieId().getMovieGenre().equals(memberMovieGenreRanking.getTopName())){
                memberMovieGenreRanking.setTopCnt(memberMovieGenreRanking.getTopCnt()-1);
            }
        });
        assertEquals(0,memberMovieGenreRanking.getTopCnt());
    }
}