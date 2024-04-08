package akatsuki.moodholic.repository;


import akatsuki.moodholic.domain.DiaryFood;
import akatsuki.moodholic.domain.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodDAO extends JpaRepository<Food,Integer> {

    boolean existsByFoodName(String s);

    Food findByFoodName(String foodName);

    @Query(value = "SELECT f.food_category, COUNT(*) " +
            "FROM food f JOIN diary_food df ON f.food_id = df.food_id " +
            "WHERE df.food_like = TRUE " +
            "GROUP BY f.food_category", nativeQuery = true)
    List<Object[]> countFoodCategorysWithLikes();

}
