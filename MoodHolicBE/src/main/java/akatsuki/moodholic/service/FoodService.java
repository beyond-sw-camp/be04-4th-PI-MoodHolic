package akatsuki.moodholic.service;

import akatsuki.moodholic.domain.Diary;
import akatsuki.moodholic.domain.DiaryFood;
import akatsuki.moodholic.domain.Food;


import java.util.List;

public interface FoodService {

    List<Food> getAllFoods();

    List<Object[]> countFoodCategorysWithLikes();

    Food findFoodByFoodName(String foodName);

    Food saveFood(Food food);
}
