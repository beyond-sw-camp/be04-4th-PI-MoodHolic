package akatsuki.moodholic.service;

import akatsuki.moodholic.domain.Diary;
import akatsuki.moodholic.domain.DiaryMovie;
import akatsuki.moodholic.domain.Movie;

import java.util.List;

public interface MovieService {

    List<Movie> getAllMovies();


    List<Object[]> countMovieGenresWithLikes();

    Movie findByMovieName(String movieName);

    Movie saveMovie(Movie movie);
}
