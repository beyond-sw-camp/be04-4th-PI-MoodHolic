package akatsuki.moodholic.service;

import akatsuki.moodholic.domain.Diary;
import akatsuki.moodholic.domain.DiaryEmotion;

import java.util.HashMap;
import java.util.List;

public interface DiaryEmotionService {
    DiaryEmotion findEmotionByDiaryId(int diaryId);

    void saveDiaryEmotion(DiaryEmotion diaryEmotion);

    void delete(int diaryId);

    HashMap<Integer, Integer> getEmotionMap(List<Diary> diaryList);
}
