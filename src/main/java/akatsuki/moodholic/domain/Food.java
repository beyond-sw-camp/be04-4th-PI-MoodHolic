package akatsuki.moodholic.domain;

import akatsuki.moodholic.etc.FOOD_CATEGORY;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "food")
public class Food {
    @Id
    @Column(name = "food_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int foodId;
    @Column(name = "food_name")
    private String foodName;
    @Enumerated(EnumType.STRING)
    @Column(name = "food_category")
    private FOOD_CATEGORY foodCategory;
    @Column(name = "food_spicy")
    private int foodSpicy;
}
