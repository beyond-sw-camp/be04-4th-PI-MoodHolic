package akatsuki.moodholic.service;

import akatsuki.moodholic.domain.DiaryEmotion;
import akatsuki.moodholic.repository.DiaryEmotionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiaryEmotionServiceImpl implements DiaryEmotionService{
    DiaryEmotionDAO diaryEmotionDAO;

    @Autowired
    public DiaryEmotionServiceImpl(DiaryEmotionDAO diaryEmotionDAO) {
        this.diaryEmotionDAO = diaryEmotionDAO;
    }

    @Override
    public DiaryEmotion findEmotionByDiaryId(int diaryId){
        return diaryEmotionDAO.findByDiaryIdDiaryId(diaryId);
    }

    @Override
    public void saveDiaryEmotion(DiaryEmotion diaryEmotion){
        diaryEmotionDAO.save(diaryEmotion);
    }

    @Override
    public void delete(int diaryId){
        diaryEmotionDAO.deleteByDiaryIdDiaryId(diaryId);
    }
}
