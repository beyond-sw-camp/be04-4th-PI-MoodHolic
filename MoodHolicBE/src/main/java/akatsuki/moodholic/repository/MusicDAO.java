package akatsuki.moodholic.repository;

import akatsuki.moodholic.domain.Music;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MusicDAO extends JpaRepository<Music,Integer> {

    Music findByMusicName(String musicName);

    @Query(value = "SELECT m.music_genre, COUNT(*) " +
            "FROM music m JOIN diary_music dm ON m.music_id = dm.music_id " +
            "WHERE dm.music_like = TRUE " +
            "GROUP BY m.music_genre", nativeQuery = true)
    List<Object[]> countMusicGenresWithLikes();

}
