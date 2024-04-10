package akatsuki.moodholic.service;

import akatsuki.moodholic.domain.Diary;
import akatsuki.moodholic.domain.DiaryMusic;
import akatsuki.moodholic.repository.DiaryMusicDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DiaryMusicServiceImpl implements DiaryMusicService{

    DiaryMusicDAO diaryMusicDAO;

    @Autowired
    public DiaryMusicServiceImpl(DiaryMusicDAO diaryMusicDAO) {
        this.diaryMusicDAO = diaryMusicDAO;
    }

    @Override
    public DiaryMusic findMusicByDiaryId(int diaryId){
        return  diaryMusicDAO.findByDiaryId(diaryId);
    }

    @Override
    public void saveDiaryMusic(DiaryMusic diaryMusic){
        diaryMusicDAO.save(diaryMusic);
    }

    @Override
    public void delete(int diaryId){
        diaryMusicDAO.deleteByDiaryId(diaryId);
    }

    @Override
    public List<DiaryMusic> getMemberLikeMusic(List<Diary> diaries){
        List<DiaryMusic> returnValue =new ArrayList<>();
        diaries.forEach(diary -> {
            DiaryMusic diaryMusic = diaryMusicDAO.findByDiaryId(diary.getDiaryId());
            if(diaryMusic!=null && diaryMusic.isMusicLike()){
                returnValue.add(diaryMusic);
            }
        });
        return returnValue;
    }


    @Override
    public List<DiaryMusic> findLikedDiaryMusics() {
        return diaryMusicDAO.findByMusicLikeTrue();
    }

    @Override
    public List<String> findLikedMusicNames() {
        return diaryMusicDAO.findLikedMusicNames();
    }

    @Override
    public void likeMusic(int diaryId, boolean music) {
        DiaryMusic diaryMusic = diaryMusicDAO.findByDiaryId(diaryId);
        diaryMusic.setMusicLike(music);
    }
}
