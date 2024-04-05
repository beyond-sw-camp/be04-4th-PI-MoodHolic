package akatsuki.moodholic.service;

import akatsuki.moodholic.domain.Movie;
import akatsuki.moodholic.repository.MovieDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService{

    private final MovieDAO movierepository;

    @Autowired
    public MovieServiceImpl(MovieDAO movierepository) {
        this.movierepository = movierepository;
    }

    @Override
    public List<Movie> getAllMovies() {
        return movierepository.findAll();
    }
}
