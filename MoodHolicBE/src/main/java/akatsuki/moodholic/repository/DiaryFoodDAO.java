package akatsuki.moodholic.repository;

import akatsuki.moodholic.domain.DiaryFood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiaryFoodDAO extends JpaRepository<DiaryFood, Integer> {

    DiaryFood findByDiaryIdDiaryId(int diaryId);

    void deleteByDiaryIdDiaryId(int diaryId);
}
