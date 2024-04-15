package akatsuki.moodholic.repository;

import akatsuki.moodholic.domain.DiaryMovie;
import akatsuki.moodholic.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieDAO extends JpaRepository<Movie, Integer> {

    Movie findByMovieName(String movieName);
    
    @Query(value = "SELECT m.movie_genre, COUNT(*) " +
            "FROM movie m JOIN diary_movie dm ON m.movie_id = dm.movie_id " +
            "WHERE dm.movie_like = TRUE " +
            "GROUP BY m.movie_genre", nativeQuery = true)
    List<Object[]> countMovieGenresWithLikes();

}
