package akatsuki.moodholic.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "food")
public class Food {

    @Id
    @Column(name = "food_id")
    private int foodId;
    @Column(name = "food_name")
    private String foodName;
//    @Column(name = "food_category")
//    private FOOD_CATEGORY foodCategory;
    @Column(name = "food_spicy")
    private int foodSpicy;
}
