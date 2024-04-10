package akatsuki.moodholic.service.facade;

import akatsuki.moodholic.domain.Diary;
import akatsuki.moodholic.domain.DiaryMusic;
import akatsuki.moodholic.domain.Music;
import akatsuki.moodholic.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicFacadeService {
    MusicService musicService;
    DiaryMusicService diaryMusicService;
    DiaryService diaryService;

    @Autowired
    public MusicFacadeService(MusicService musicService, DiaryMusicService diaryMusicService, DiaryService diaryService) {
        this.musicService = musicService;
        this.diaryMusicService = diaryMusicService;
        this.diaryService = diaryService;
    }

    public List<Music> getAllMusics() {
        return musicService.getAllMusics();
    }

    public List<DiaryMusic> findLikedDiaryMusics() {
        return diaryMusicService.findLikedDiaryMusics();
    }

    public List<String> findLikedMusicNames(){
        return diaryMusicService.findLikedMusicNames();
    }

    public List<DiaryMusic> getMemberLikeMusic(long memberId){
        List<Diary> diaries = diaryService.getMemberDiaries(memberId);
        return diaryMusicService.getMemberLikeMusic(diaries);
    }

    public List<Object[]> countMusicGenresWithLikes() {
        return musicService.countMusicGenresWithLikes();
    }
}
