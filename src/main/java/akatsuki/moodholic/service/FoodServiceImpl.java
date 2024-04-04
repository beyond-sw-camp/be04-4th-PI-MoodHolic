package akatsuki.moodholic.service;

import akatsuki.moodholic.domain.Food;
import akatsuki.moodholic.repository.FoodDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodServiceImpl implements FoodService {

    private final FoodDAO foodrepositoy;

    @Autowired
    public FoodServiceImpl(FoodDAO foodrepositoy) {
        this.foodrepositoy = foodrepositoy;
    }

    @Override
    public List<Food> getAllFoods() {
        return foodrepositoy.findAll();
    }
}
