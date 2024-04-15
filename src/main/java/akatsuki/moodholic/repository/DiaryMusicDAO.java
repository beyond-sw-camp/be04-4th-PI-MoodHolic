package akatsuki.moodholic.repository;

import akatsuki.moodholic.domain.DiaryMusic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiaryMusicDAO extends JpaRepository<DiaryMusic, Integer> {

    DiaryMusic findByDiaryId(int diaryId);

    List<DiaryMusic> findByMusicLikeTrue();

    @Query(value = "SELECT m.music_name FROM music m INNER JOIN diary_music dm ON m.music_id = dm.music_id WHERE dm.music_like = true", nativeQuery = true)
    List<String> findLikedMusicNames();

    void deleteByDiaryId(int diaryId);
}
