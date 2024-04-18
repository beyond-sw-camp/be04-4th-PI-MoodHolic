package akatsuki.moodholic.repository;

import akatsuki.moodholic.domain.DiaryFood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiaryFoodDAO extends JpaRepository<DiaryFood, Integer> {

    DiaryFood findByDiaryId(int diaryId);

    List<DiaryFood> findByFoodLikeTrue();

    @Query(value = "SELECT f.food_name FROM food f INNER JOIN diary_food df ON f.food_id = df.food_id WHERE df.food_like = true", nativeQuery = true)
    List<String> findLikedFoodNames();


    void deleteByDiaryId(int diaryId);

}
