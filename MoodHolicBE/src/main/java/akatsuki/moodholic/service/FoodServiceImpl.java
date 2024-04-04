package akatsuki.moodholic.service;

import akatsuki.moodholic.domain.DiaryFood;
import akatsuki.moodholic.domain.Food;
import akatsuki.moodholic.repository.DiaryFoodDAO;
import akatsuki.moodholic.repository.FoodDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodServiceImpl implements FoodService {

    private final FoodDAO foodrepositoy;
    private final DiaryFoodDAO diaryfoodrepository;

    @Autowired
    public FoodServiceImpl(FoodDAO foodrepositoy, DiaryFoodDAO diaryfoodrepository) {
        this.foodrepositoy = foodrepositoy;
        this.diaryfoodrepository = diaryfoodrepository;
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



}
