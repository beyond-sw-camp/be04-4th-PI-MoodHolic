package akatsuki.moodholic.service;

import akatsuki.moodholic.domain.DiaryMovie;
import akatsuki.moodholic.domain.Movie;
import akatsuki.moodholic.repository.DiaryMovieDAO;
import akatsuki.moodholic.repository.MovieDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService{

    private final MovieDAO movierepository;
    private final DiaryMovieDAO diarymovierepository;

    @Autowired
    public MovieServiceImpl(MovieDAO movierepository, DiaryMovieDAO diarymovierepository) {
        this.movierepository = movierepository;
        this.diarymovierepository = diarymovierepository;
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
}
