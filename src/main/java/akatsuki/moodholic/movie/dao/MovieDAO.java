package akatsuki.moodholic.movie.dao;

import akatsuki.moodholic.movie.dto.MovieDTO;
import akatsuki.moodholic.movie.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieDAO extends JpaRepository<MovieEntity, Integer> {

}
