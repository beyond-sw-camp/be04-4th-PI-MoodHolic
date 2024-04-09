package akatsuki.moodholic.service;

import akatsuki.moodholic.domain.Diary;
import akatsuki.moodholic.domain.DiaryMovie;
import akatsuki.moodholic.domain.Movie;
import akatsuki.moodholic.repository.DiaryDAO;
import akatsuki.moodholic.repository.DiaryMovieDAO;
import akatsuki.moodholic.repository.MovieDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService{

    private final MovieDAO movierepository;
    private final DiaryMovieDAO diarymovierepository;
    private final DiaryDAO diaryDAO;

    @Autowired
    public MovieServiceImpl(MovieDAO movierepository, DiaryMovieDAO diarymovierepository,DiaryDAO diaryDAO) {
        this.movierepository = movierepository;
        this.diarymovierepository = diarymovierepository;
        this.diaryDAO = diaryDAO;
    }

    @Override
    public List<Movie> getAllMovies() {
        return movierepository.findAll();
    }

    @Override
    public List<DiaryMovie> findLikedDiaryMovies() {
        return diarymovierepository.findByMovieLikeTrue();
    }

    @Override
    public List<String> findLikedMovieNames() {
        return diarymovierepository.findLikedMovieNames();
    }

    @Override
    public List<Object[]> countMovieGenresWithLikes() {
        return movierepository.countMovieGenresWithLikes();
    }

    @Override
    public List<DiaryMovie> getMemberLikedMovie(long memberId){
        List<Diary> diaries = diaryDAO.findAllByMemberMemberId(memberId);
        List<DiaryMovie> returnValue= new ArrayList<>();
        diaries.forEach(diary -> {
            DiaryMovie diaryMovie = diarymovierepository.findByDiaryId(diary.getDiaryId());
            if(diaryMovie!=null&&diaryMovie.isMovieLike()==true)
                returnValue.add(diaryMovie);
        });
        return returnValue;
    }
    @Override
    public Movie findByMovieName(String movieName){
        return movierepository.findByMovieName(movieName);
    }

    @Override
    public Movie saveMovie(Movie movie){
        return movierepository.save(movie);
    }

}
