package akatsuki.moodholic.service.facade;

import akatsuki.moodholic.domain.Diary;
import akatsuki.moodholic.domain.DiaryMovie;
import akatsuki.moodholic.domain.Movie;
import akatsuki.moodholic.dto.MemberMovieGenreRanking;
import akatsuki.moodholic.service.DiaryMovieService;
import akatsuki.moodholic.service.DiaryService;
import akatsuki.moodholic.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class MovieFacadeService {
    MovieService movieService;
    DiaryMovieService diaryMovieService;
    DiaryService diaryService;
    int maximum = 0;
    String name="";
    @Autowired
    public MovieFacadeService(MovieService movieService, DiaryMovieService diaryMovieService, DiaryService diaryService) {
        this.movieService = movieService;
        this.diaryMovieService = diaryMovieService;
        this.diaryService = diaryService;
    }
    private void init() {
        maximum=0;
        name="";
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
        return diaryMovieService.getMemberLikedMovie(memberId);
    }

    public MemberMovieGenreRanking getMemberMovieGenreRanking(long memberId) {
        init();
        List<DiaryMovie> diaryMovies= diaryMovieService.getMemberLikedMovie(memberId);
        HashMap<String, Integer> lists = new HashMap<>();
        System.out.println("diaryMovies = " + diaryMovies);

        diaryMovies.forEach(diaryMovie -> {
            if(lists.get(diaryMovie.getMovieId().getMovieGenre())==null) {
                lists.put(diaryMovie.getMovieId().getMovieGenre(), 1);
            }else{
                int data = lists.get(diaryMovie.getMovieId().getMovieGenre());
                if (maximum<data+1){
                    maximum=data+1;
                    name=diaryMovie.getMovieId().getMovieGenre();
                }
                lists.replace(diaryMovie.getMovieId().getMovieGenre(), data + 1);
            }
        });
        return new MemberMovieGenreRanking(lists,maximum,name);
    }

    public MemberMovieGenreRanking getMemberMovieGenreRanking2(long memberId) {
        init();
        List<Diary> diaries = diaryService.getMemberDiaries(memberId);
        List<DiaryMovie> diaryMovies= diaryMovieService.getMemberLikedMovie2(diaries);
        HashMap<String, Integer> lists = new HashMap<>();

        diaryMovies.forEach(diaryMovie -> {

            if(diaryMovie!=null&& diaryMovie.getMovieId()!=null && lists.get(diaryMovie.getMovieId().getMovieGenre())==null) {
                lists.put(diaryMovie.getMovieId().getMovieGenre(), 1);
            }else if(diaryMovie!=null&& diaryMovie.getMovieId()!=null){
                int data = lists.get(diaryMovie.getMovieId().getMovieGenre());
                if (maximum<data+1){
                    maximum=data+1;
                    name=diaryMovie.getMovieId().getMovieGenre();
                }
                lists.replace(diaryMovie.getMovieId().getMovieGenre(), data + 1);
            }
        });
        return new MemberMovieGenreRanking(lists,maximum,name);
    }
}
