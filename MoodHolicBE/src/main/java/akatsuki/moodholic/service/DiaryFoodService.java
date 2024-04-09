package akatsuki.moodholic.service;

import akatsuki.moodholic.domain.DiaryFood;

public interface DiaryFoodService {
    DiaryFood findFoodByDiaryId(int diaryId);

    void saveDiaryFood(DiaryFood diaryFood);

    void delete(int diaryId);
}
