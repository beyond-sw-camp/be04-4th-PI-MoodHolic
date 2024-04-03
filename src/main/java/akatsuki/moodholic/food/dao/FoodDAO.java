package akatsuki.moodholic.food.dao;


import akatsuki.moodholic.food.entity.FoodEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodDAO extends JpaRepository<FoodEntity,Integer> {
}
