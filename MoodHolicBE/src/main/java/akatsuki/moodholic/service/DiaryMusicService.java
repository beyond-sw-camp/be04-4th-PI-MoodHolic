package akatsuki.moodholic.service;

import akatsuki.moodholic.domain.Diary;
import akatsuki.moodholic.domain.DiaryMusic;

import java.util.List;

public interface DiaryMusicService {
    DiaryMusic findMusicByDiaryId(int diaryId);

    void saveDiaryMusic(DiaryMusic diaryMusic);

    void delete(int diaryId);

    List<DiaryMusic> getMemberLikeMusic(List<Diary> diaries);
}
