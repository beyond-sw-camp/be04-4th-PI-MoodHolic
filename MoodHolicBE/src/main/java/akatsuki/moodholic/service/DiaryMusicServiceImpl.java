package akatsuki.moodholic.service;

import akatsuki.moodholic.domain.DiaryMusic;
import akatsuki.moodholic.repository.DiaryMusicDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
