package akatsuki.moodholic.service;

import akatsuki.moodholic.domain.Diary;
import akatsuki.moodholic.domain.DiaryEmotion;
import akatsuki.moodholic.repository.DiaryEmotionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

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

    @Override
    public HashMap<Integer, Integer> getEmotionMap(List<Diary> diaryList) {
        HashMap<Integer,Integer> returnValue= new HashMap<>();
        diaryList.forEach(diary -> {
            DiaryEmotion diaryEmotion = diaryEmotionDAO.findByDiaryIdDiaryId(diary.getDiaryId());
            if(diaryEmotion.getDiaryId()!=null){
                returnValue.put(diaryEmotion.getDiaryId().getDiaryId(),diaryEmotion.getEmotionId());
            }
        });
        return returnValue;
    }
}
