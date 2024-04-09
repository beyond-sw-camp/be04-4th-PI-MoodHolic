package akatsuki.moodholic.service;

import akatsuki.moodholic.domain.DiaryMusic;

public interface DiaryMusicService {
    DiaryMusic findMusicByDiaryId(int diaryId);

    void saveDiaryMusic(DiaryMusic diaryMusic);

    void delete(int diaryId);
}
