package akatsuki.moodholic.service.facade;

import akatsuki.moodholic.domain.*;
import akatsuki.moodholic.dto.MemberFoodGenreRanking;
import akatsuki.moodholic.dto.MemberMovieGenreRanking;
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
    int maximum = 0;
    String name="";
    private void init() {
        maximum=0;
        name="";
    }
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

    public MemberFoodGenreRanking getMemberFoodGenreRanking(long memberId) {
        init();
        List<Diary> diaries = diaryService.getMemberDiaries(memberId);
        List<DiaryFood> diaryFoods= diaryFoodService.getMemberLikeFood(diaries);
        HashMap<String, Integer> lists = new HashMap<>();

        diaryFoods.forEach(diaryFood -> {
            if(lists.get(diaryFood.getFoodId().getFoodCategory().name())==null) {
                lists.put(diaryFood.getFoodId().getFoodCategory().name(), 1);
            }else{
                int data = lists.get(diaryFood.getFoodId().getFoodCategory().name());
                if (maximum<data+1){
                    maximum=data+1;
                    name=diaryFood.getFoodId().getFoodCategory().name();
                }
                lists.replace(diaryFood.getFoodId().getFoodCategory().name(), data + 1);
            }
        });

        return new MemberFoodGenreRanking(lists,maximum,name);

    }
}
