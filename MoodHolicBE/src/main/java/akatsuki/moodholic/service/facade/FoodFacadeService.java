package akatsuki.moodholic.service.facade;

import akatsuki.moodholic.domain.Diary;
import akatsuki.moodholic.domain.DiaryFood;
import akatsuki.moodholic.domain.Food;
import akatsuki.moodholic.repository.DiaryFoodDAO;
import akatsuki.moodholic.service.DiaryFoodService;
import akatsuki.moodholic.service.DiaryService;
import akatsuki.moodholic.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class FoodFacadeService {
    private  final FoodService foodService;
    private final DiaryService diaryService;
    private final DiaryFoodService diaryFoodService;

    @Autowired
    public FoodFacadeService(FoodService foodService, DiaryService diaryService, DiaryFoodService diaryFoodService) {
        this.foodService = foodService;
        this.diaryService = diaryService;
        this.diaryFoodService = diaryFoodService;
    }


    public List<Food> getAllFoods() {
        return foodService.getAllFoods();
    }

    public List<DiaryFood> findLikedDiaryFoods() {
        return diaryFoodService.findLikedDiaryFoods();
    }

    public HashMap<String,Integer> countFoodCategoryWithMemberLike(long memberId){
        List<Diary> diaries = diaryService.getMemberDiaries(memberId);
        HashMap<String,Integer> returnValue = diaryFoodService.countMembersFoodLike(diaries);
        System.out.println("returnValue = " + returnValue);
        return returnValue;
    }

    public List<String> findLikedFoodNames() {
        return diaryFoodService.findLikedFoodNames();
    }

    public List<Object[]> countFoodCategorysWithLikes() {
        return foodService.countFoodCategorysWithLikes();
    }

    public List<DiaryFood> getMemberLikeFood(long memberId) {
        List<Diary> diaries = diaryService.getMemberDiaries(memberId);
        return diaryFoodService.getMemberLikeFood(diaries);
    }

}
