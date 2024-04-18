package akatsuki.moodholic.service;

import akatsuki.moodholic.domain.Movie;
import akatsuki.moodholic.repository.MovieDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService{

    private final MovieDAO movieDAO;

    @Autowired
    public MovieServiceImpl(MovieDAO movierepository) {
        this.movieDAO = movierepository;
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieDAO.findAll();
    }


    @Override
    public List<Object[]> countMovieGenresWithLikes() {
        return movieDAO.countMovieGenresWithLikes();
    }


    @Override
    public Movie findByMovieName(String movieName){
        return movieDAO.findByMovieName(movieName);
    }

    @Override
    public Movie saveMovie(Movie movie){
        return movieDAO.save(movie);
    }

}
