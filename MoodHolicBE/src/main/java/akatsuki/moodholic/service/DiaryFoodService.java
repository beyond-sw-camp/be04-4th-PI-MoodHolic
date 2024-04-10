package akatsuki.moodholic.service;

import akatsuki.moodholic.domain.Diary;
import akatsuki.moodholic.domain.DiaryFood;

import java.util.HashMap;
import java.util.List;

public interface DiaryFoodService {
    DiaryFood findFoodByDiaryId(int diaryId);

    void saveDiaryFood(DiaryFood diaryFood);

    void delete(int diaryId);

    List<DiaryFood> findLikedDiaryFoods();

    List<String> findLikedFoodNames();

    List<DiaryFood> getMemberLikeFood(List<Diary> diaries);

    HashMap<String, Integer> countMembersFoodLike(List<Diary> Diaries);

    void likeFood(int diaryId, boolean food);
}

