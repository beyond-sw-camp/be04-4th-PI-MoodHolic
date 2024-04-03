package akatsuki.moodholic.repository;


import akatsuki.moodholic.domain.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodDAO extends JpaRepository<Food,Integer> {
}
