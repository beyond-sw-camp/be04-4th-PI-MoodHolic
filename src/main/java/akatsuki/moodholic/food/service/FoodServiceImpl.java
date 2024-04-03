package akatsuki.moodholic.food.service;

import akatsuki.moodholic.food.dao.FoodDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodServiceImpl implements FoodService{
    private FoodDAO foodDAO;

    @Autowired
    public FoodServiceImpl(FoodDAO foodDAO) {
        this.foodDAO = foodDAO;
    }
}
