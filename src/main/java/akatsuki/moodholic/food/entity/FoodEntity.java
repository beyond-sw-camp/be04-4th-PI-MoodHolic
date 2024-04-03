package akatsuki.moodholic.food.entity;

import akatsuki.moodholic.food.FOOD_CATEGORY;
import jakarta.persistence.Column;
import jakarta.persistence.Id;

public class FoodEntity {

    @Id
    @Column(name = "food_id")
    private int foodId;
    @Column(name = "food_name")
    private String foodName;
    @Column(name = "food_category")
    private FOOD_CATEGORY foodCategory;
    @Column(name = "food_spicy")
    private int foodSpicy;
}
