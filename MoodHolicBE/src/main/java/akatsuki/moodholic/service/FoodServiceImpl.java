package akatsuki.moodholic.service;

import akatsuki.moodholic.domain.Diary;
import akatsuki.moodholic.domain.DiaryFood;
import akatsuki.moodholic.domain.Food;
import akatsuki.moodholic.repository.DiaryDAO;
import akatsuki.moodholic.repository.DiaryFoodDAO;
import akatsuki.moodholic.repository.FoodDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FoodServiceImpl implements FoodService {

    private final FoodDAO foodrepositoy;
    private final DiaryFoodDAO diaryfoodrepository;
    private final DiaryDAO diaryDAO;

    @Autowired
    public FoodServiceImpl(FoodDAO foodrepositoy, DiaryFoodDAO diaryfoodrepository, DiaryDAO diaryDAO) {
        this.foodrepositoy = foodrepositoy;
        this.diaryfoodrepository = diaryfoodrepository;
        this.diaryDAO = diaryDAO;
    }

    @Override
    public List<Food> getAllFoods() {
        return foodrepositoy.findAll();
    }

    @Override
    public List<DiaryFood> findLikedDiaryFoods() {
        return diaryfoodrepository.findByFoodLikeTrue();
    }

    @Override
    public List<String> findLikedFoodNames() {
        return diaryfoodrepository.findLikedFoodNames();
    }

    @Override
    public List<Object[]> countFoodCategorysWithLikes() {
        return foodrepositoy.countFoodCategorysWithLikes();
    }

    @Override
    public List<DiaryFood> getMemberLikeFood(long memberId){
        List<Diary> diaries = diaryDAO.findAllByMemberMemberId(memberId);
        List<DiaryFood> returnValue = new ArrayList<>();
        diaries.forEach(diary -> {
            DiaryFood diaryFood = diaryfoodrepository.findByDiaryId(diary.getDiaryId());
            if(diaryFood.getFoodLike()==true){
                returnValue.add(diaryFood);
            }
        });
        return returnValue;
    }

    @Override
    public Food findFoodByFoodName(String foodName){
        return foodrepositoy.findByFoodName(foodName);
    }

    @Override
    public Food saveFood(Food food){
        return foodrepositoy.save(food);
    }

}
