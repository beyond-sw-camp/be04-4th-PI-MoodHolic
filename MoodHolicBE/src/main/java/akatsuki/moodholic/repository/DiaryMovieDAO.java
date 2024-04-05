package akatsuki.moodholic.repository;

import akatsuki.moodholic.domain.DiaryMovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiaryMovieDAO extends JpaRepository<DiaryMovie, Integer> {

    DiaryMovie findByDiaryIdDiaryId(int diaryId);

    void deleteByDiaryIdDiaryId(int diaryId);
}
