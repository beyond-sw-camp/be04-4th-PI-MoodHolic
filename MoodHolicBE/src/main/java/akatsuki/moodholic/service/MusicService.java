package akatsuki.moodholic.service;

import akatsuki.moodholic.domain.DiaryMusic;
import akatsuki.moodholic.domain.Music;

import java.util.List;

public interface MusicService {

    List<Music> getAllMusics();

    List<DiaryMusic> findLikedDiaryMusics();

    List<String> findLikedMusicNames();
}
