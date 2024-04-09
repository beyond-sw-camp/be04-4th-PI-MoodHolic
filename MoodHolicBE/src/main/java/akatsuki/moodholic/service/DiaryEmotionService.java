package akatsuki.moodholic.service;

import akatsuki.moodholic.domain.DiaryEmotion;

public interface DiaryEmotionService {
    DiaryEmotion findEmotionByDiaryId(int diaryId);

    void saveDiaryEmotion(DiaryEmotion diaryEmotion);

    void delete(int diaryId);
}
