package akatsuki.moodholic.service;

import akatsuki.moodholic.domain.Diary;
import akatsuki.moodholic.domain.DiaryEmotion;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

public interface DiaryEmotionService {
    DiaryEmotion findEmotionByDiaryId(int diaryId);

    void saveDiaryEmotion(DiaryEmotion diaryEmotion);

    void delete(int diaryId);

    Map<Diary, Integer> getEmotionMap(long memberId);

//    SortedMap<Diary, Double> getEmotionDayMap(long memberId);
}
