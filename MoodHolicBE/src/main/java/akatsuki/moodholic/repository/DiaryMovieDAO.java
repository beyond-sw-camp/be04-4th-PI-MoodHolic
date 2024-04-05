package akatsuki.moodholic.repository;

import akatsuki.moodholic.domain.DiaryMovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiaryMovieDAO extends JpaRepository<DiaryMovie, Integer> {



    DiaryMovie findByDiaryId(int diaryId);

    List<DiaryMovie> findByMovieLikeTrue();

    @Query(value = "SELECT m.movie_name FROM movie m INNER JOIN diary_movie dm ON m.movie_id = dm.movie_id WHERE dm.movie_like = true", nativeQuery = true)
    List<String> findLikedMovieNames();


    void deleteByDiaryId(int diaryId);
}
