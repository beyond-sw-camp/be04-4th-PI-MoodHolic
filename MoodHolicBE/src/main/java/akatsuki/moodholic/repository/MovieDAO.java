package akatsuki.moodholic.repository;

import akatsuki.moodholic.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieDAO extends JpaRepository<Movie, Integer> {

    Movie findByMovieName(String movieName);
}
