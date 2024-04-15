package akatsuki.moodholic.service;

import akatsuki.moodholic.domain.DiaryMusic;
import akatsuki.moodholic.domain.Music;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MusicService {

    List<Music> getAllMusics();

    List<Object[]> countMusicGenresWithLikes();

    Music findByMusicName(String musicName);

    Music saveMusic(Music music);
}
