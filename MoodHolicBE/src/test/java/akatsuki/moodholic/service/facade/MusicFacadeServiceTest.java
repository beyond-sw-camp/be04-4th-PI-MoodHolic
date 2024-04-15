package akatsuki.moodholic.service.facade;

import akatsuki.moodholic.domain.Diary;
import akatsuki.moodholic.domain.DiaryMovie;
import akatsuki.moodholic.domain.DiaryMusic;
import akatsuki.moodholic.domain.Music;
import akatsuki.moodholic.dto.MemberMusicGenreRanking;
import akatsuki.moodholic.repository.DiaryMusicDAO;
import akatsuki.moodholic.repository.MusicDAO;
import akatsuki.moodholic.service.DiaryMusicService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MusicFacadeServiceTest {
    @Autowired
    MusicFacadeService musicFacadeService;
    @Autowired
    MusicDAO musicDAO;
    @Autowired
    DiaryMusicDAO diaryMusicDAO;
    @Autowired
    DiaryFacadeService diaryFacadeService;
    @Autowired
    DiaryMusicService diaryMusicService;

    @Test
    @DisplayName("모든 음악 불러오기")
    void getAllMusics() {
        List<Music> musicList1 = musicFacadeService.getAllMusics();
        List<Music> musicList2 = musicDAO.findAll();
        assertEquals(musicList1,musicList2);
    }

    @Test
    @DisplayName("멤버가 좋아요 표시한 음악 리스트")
    void getMemberLikeMusic() {
        long memberId=1;
        List<DiaryMusic> diaryMusicList = musicFacadeService.getMemberLikeMusic(memberId);
        diaryMusicList.forEach(diaryMusic -> {
            assertEquals(true, diaryMusic.isMusicLike());
        });
    }

    @Test
    @DisplayName("멤버가 좋아요 한 음악 카테고리 랭킹")
    void getMemberMusicGenreRanking() {
        long memberId =1;
        MemberMusicGenreRanking memberMusicGenreRanking = musicFacadeService.getMemberMusicGenreRanking(memberId);
        List<Diary> diaryList = diaryFacadeService.getMemberDiary(memberId);
        List<DiaryMusic> diaryMovieList = diaryMusicService.getMemberLikeMusic(diaryList);
        diaryMovieList.forEach(diaryMusic -> {
            if(diaryMusic.getMusicId().getMusicGenre().equals(memberMusicGenreRanking.getTopName())){
                memberMusicGenreRanking.setTopCnt(memberMusicGenreRanking.getTopCnt()-1);
            }
        });
        assertEquals(0,memberMusicGenreRanking.getTopCnt());
    }
}