package akatsuki.moodholic.music.dao;

import akatsuki.moodholic.music.entity.MusicEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicDAO extends JpaRepository<MusicEntity,Integer> {
    
}
