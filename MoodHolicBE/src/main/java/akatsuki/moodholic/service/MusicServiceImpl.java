package akatsuki.moodholic.service;

import akatsuki.moodholic.domain.Diary;
import akatsuki.moodholic.domain.DiaryMusic;
import akatsuki.moodholic.domain.Music;
import akatsuki.moodholic.repository.DiaryDAO;
import akatsuki.moodholic.repository.DiaryMusicDAO;
import akatsuki.moodholic.repository.MusicDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MusicServiceImpl implements MusicService{

    private final MusicDAO musicrepository;
    private final DiaryMusicDAO diarymusicrepository;
    private final DiaryDAO diaryDAO;

    @Autowired
    public MusicServiceImpl(MusicDAO musicrepository, DiaryMusicDAO diarymusicrepository, DiaryDAO diaryDAO) {
        this.musicrepository = musicrepository;
        this.diarymusicrepository = diarymusicrepository;
        this.diaryDAO = diaryDAO;
    }

    @Override
    public List<Music> getAllMusics() {
        return musicrepository.findAll();
    }

    @Override
    public List<DiaryMusic> findLikedDiaryMusics() {
        return diarymusicrepository.findByMusicLikeTrue();
    }

    @Override
    public List<String> findLikedMusicNames() {
        return diarymusicrepository.findLikedMusicNames();
    }

    @Override
    public List<Object[]> countMusicGenresWithLikes() {
        return musicrepository.countMusicGenresWithLikes();
    }

    @Override
    public List<DiaryMusic> getMemberLikeMusic(long memberId){
        List<Diary> diaries = diaryDAO.findAllByMemberMemberId(memberId);
        List<DiaryMusic> returnValue =new ArrayList<>();
        diaries.forEach(diary -> {
            DiaryMusic diaryMusic = diarymusicrepository.findByDiaryId(diary.getDiaryId());
            if(diaryMusic!=null && diaryMusic.isMusicLike()){
                returnValue.add(diaryMusic);
            }
        });
        return returnValue;
    }

}
