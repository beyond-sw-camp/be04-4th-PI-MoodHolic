package akatsuki.moodholic.food.dto;

import akatsuki.moodholic.food.FOOD_CATEGORY;
import lombok.Data;

@Data
public class FoodDTO {
    private int foodId;
    private String foodName;
    private FOOD_CATEGORY foodCategory;
    private int foodSpicy;
}
