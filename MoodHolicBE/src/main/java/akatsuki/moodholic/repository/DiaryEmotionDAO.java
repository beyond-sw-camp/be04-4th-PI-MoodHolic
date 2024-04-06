package akatsuki.moodholic.repository;

import akatsuki.moodholic.domain.DiaryEmotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiaryEmotionDAO extends JpaRepository<DiaryEmotion, Integer> {

    DiaryEmotion findByDiaryIdDiaryId(int diaryId);

    void deleteByDiaryIdDiaryId(int diaryId);
}
