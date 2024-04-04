package akatsuki.moodholic.repository;

import akatsuki.moodholic.domain.Emotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmotionDAO  extends JpaRepository<Emotion,Integer> {
}
