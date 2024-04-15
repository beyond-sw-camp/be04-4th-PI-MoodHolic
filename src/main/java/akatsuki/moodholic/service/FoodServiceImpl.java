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

    private final FoodDAO foodDAO;

    @Autowired
    public FoodServiceImpl(FoodDAO foodDAO) {
        this.foodDAO = foodDAO;
    }

    @Override
    public List<Food> getAllFoods() {
        return foodDAO.findAll();
    }

    @Override
    public List<Object[]> countFoodCategorysWithLikes() {
        return foodDAO.countFoodCategorysWithLikes();
    }

    @Override
    public Food findFoodByFoodName(String foodName){
        return foodDAO.findByFoodName(foodName);
    }

    @Override
    public Food saveFood(Food food){
        return foodDAO.save(food);
    }

}
