package akatsuki.moodholic.repository;

import akatsuki.moodholic.domain.Music;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicDAO extends JpaRepository<Music,Integer> {
}
