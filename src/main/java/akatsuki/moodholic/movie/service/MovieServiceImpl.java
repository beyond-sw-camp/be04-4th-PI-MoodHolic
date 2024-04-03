package akatsuki.moodholic.movie.service;

import akatsuki.moodholic.movie.dao.MovieDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl implements MovieService{
    private MovieDAO movieDAO;
    @Autowired
    public MovieServiceImpl(MovieDAO movieDAO) {
        this.movieDAO = movieDAO;
    }
}
