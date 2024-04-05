package akatsuki.moodholic.service;

import akatsuki.moodholic.domain.DiaryFood;
import akatsuki.moodholic.domain.Food;


import java.util.List;

public interface FoodService {

    List<Food> getAllFoods();

    List<DiaryFood> findLikedDiaryFoods();

    List<String> findLikedFoodNames();

    List<Object[]> countFoodCategorysWithLikes();
}
