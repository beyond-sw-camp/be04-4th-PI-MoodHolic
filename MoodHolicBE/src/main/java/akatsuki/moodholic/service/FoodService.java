package akatsuki.moodholic.service;

import akatsuki.moodholic.domain.DiaryFood;
import akatsuki.moodholic.domain.Food;
import akatsuki.moodholic.dto.FoodView;

import java.util.List;

public interface FoodService {

    List<Food> getAllFoods();

    List<DiaryFood> findLikedDiaryFoods();

    List<String> findLikedFoodNames();
}
