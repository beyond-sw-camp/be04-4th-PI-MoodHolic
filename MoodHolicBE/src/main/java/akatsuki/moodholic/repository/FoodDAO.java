package akatsuki.moodholic.repository;


import akatsuki.moodholic.domain.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodDAO extends JpaRepository<Food,Integer> {
    boolean existsByFoodName(String s);

    Food findByFoodName(String foodName);
}
