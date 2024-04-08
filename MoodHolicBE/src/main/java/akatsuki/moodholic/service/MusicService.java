package akatsuki.moodholic.service;

import akatsuki.moodholic.domain.DiaryMusic;
import akatsuki.moodholic.domain.Music;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MusicService {

    List<Music> getAllMusics();

    List<DiaryMusic> findLikedDiaryMusics();

    List<String> findLikedMusicNames();

    List<Object[]> countMusicGenresWithLikes();

    List<DiaryMusic> getMemberLikeMusic(long memberId);
}
