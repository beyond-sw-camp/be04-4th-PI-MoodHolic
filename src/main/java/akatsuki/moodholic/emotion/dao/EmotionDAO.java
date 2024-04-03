package akatsuki.moodholic.emotion.dao;

import akatsuki.moodholic.emotion.entity.EmotionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmotionDAO  extends JpaRepository<EmotionEntity,Integer> {
}
