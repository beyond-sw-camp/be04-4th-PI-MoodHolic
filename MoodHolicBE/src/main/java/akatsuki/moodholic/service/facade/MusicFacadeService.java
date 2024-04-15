package akatsuki.moodholic.service.facade;

import akatsuki.moodholic.domain.Diary;
import akatsuki.moodholic.domain.DiaryMusic;
import akatsuki.moodholic.domain.Music;
import akatsuki.moodholic.dto.MemberMusicGenreRanking;
import akatsuki.moodholic.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class MusicFacadeService {
    MusicService musicService;
    DiaryMusicService diaryMusicService;
    DiaryService diaryService;
    int maximum = 0;
    String name="";
    private void init() {
        maximum=0;
        name="";
    }

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


    public MemberMusicGenreRanking getMemberMusicGenreRanking(long memberId){
        init();
        List<Diary> diaries = diaryService.getMemberDiaries(memberId);
        List<DiaryMusic> diaryMusics = diaryMusicService.getMemberLikeMusic(diaries);
        HashMap<String, Integer> lists = new HashMap<>();

        diaryMusics.forEach(diaryMusic -> {
            if(lists.get(diaryMusic.getMusicId().getMusicGenre())==null){
                lists.put(diaryMusic.getMusicId().getMusicGenre(),1);
            }else{
                int data = lists.get(diaryMusic.getMusicId().getMusicGenre());
                if(maximum<data+1){
                    maximum=data+1;
                    name=diaryMusic.getMusicId().getMusicGenre();
                }
                lists.replace(diaryMusic.getMusicId().getMusicGenre(),data+1);
            }
        });
        return new MemberMusicGenreRanking(lists,maximum,name);
    }
}
