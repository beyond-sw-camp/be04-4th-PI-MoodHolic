package akatsuki.moodholic.service;

import akatsuki.moodholic.domain.Diary;
import akatsuki.moodholic.domain.DiaryFood;
import akatsuki.moodholic.repository.DiaryFoodDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class DiaryFoodServiceImpl implements DiaryFoodService{
    DiaryFoodDAO diaryFoodDAO;

    @Autowired
    public DiaryFoodServiceImpl(DiaryFoodDAO diaryFoodDAO) {
        this.diaryFoodDAO = diaryFoodDAO;
    }

    @Override
    public DiaryFood findFoodByDiaryId(int diaryId){
        return diaryFoodDAO.findByDiaryId(diaryId);
    }

    @Override
    public void saveDiaryFood(DiaryFood diaryFood){
        diaryFoodDAO.save(diaryFood);
    }

    @Override
    public void delete(int diaryId){
        diaryFoodDAO.deleteByDiaryId(diaryId);
    }

    @Override
    public List<DiaryFood> findLikedDiaryFoods(){
        return diaryFoodDAO.findByFoodLikeTrue();
    }
    @Override
    public List<String> findLikedFoodNames() {
        return diaryFoodDAO.findLikedFoodNames();
    }

    @Override
    public List<DiaryFood> getMemberLikeFood(List<Diary> diaries){
        List<DiaryFood> returnValue = new ArrayList<>();
        diaries.forEach(diary -> {
            DiaryFood diaryFood = diaryFoodDAO.findByDiaryId(diary.getDiaryId());
            if(diaryFood.getFoodLike()==true){
                returnValue.add(diaryFood);
            }
        });
        return returnValue;
    }

    @Override
    public HashMap<String, Integer> countMembersFoodLike(List<Diary> diaries){
        HashMap<String,Integer> returnValue = new HashMap<>();
        List<DiaryFood> diaryFoods = new ArrayList<>();
        diaries.forEach(diary -> {
            DiaryFood diaryFood = diaryFoodDAO.findByDiaryId(diary.getDiaryId());
            if(diaryFood.getFoodLike()==true){
                diaryFoods.add(diaryFood);
            }
        });
        diaryFoods.forEach(diaryFood -> {
            String key = diaryFood.getFoodId().getFoodName();
            if(key!=null) {
                if (returnValue.containsKey(key)) {
                    int v = returnValue.get(key);
                    returnValue.replace(key, v + 1);
                } else {
                    returnValue.put(key, 1);
                }
            }
        });
        return  returnValue;
    }

    @Override
    @Transactional
    public void likeFood(int diaryId, boolean food) {
        DiaryFood diaryFood = diaryFoodDAO.findByDiaryId(diaryId);
        diaryFood.setFoodLike(food);
    }

}
