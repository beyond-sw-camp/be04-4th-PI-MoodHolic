package akatsuki.moodholic.repository;

import akatsuki.moodholic.domain.DiaryMusic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiaryMusicDAO extends JpaRepository<DiaryMusic, Integer> {
}
