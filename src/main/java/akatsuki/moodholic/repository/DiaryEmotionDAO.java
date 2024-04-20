package akatsuki.moodholic.repository;

import akatsuki.moodholic.domain.DiaryEmotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiaryEmotionDAO extends JpaRepository<DiaryEmotion, Integer> {

    DiaryEmotion findByDiaryDiaryId(int diaryId);

    void deleteByDiaryDiaryId(int diaryId);

    DiaryEmotion findByDiaryDiaryIdOrderByDiaryDateAsc(int diaryId);

    List<DiaryEmotion> findByDiaryMemberMemberId(long memberId);
}
