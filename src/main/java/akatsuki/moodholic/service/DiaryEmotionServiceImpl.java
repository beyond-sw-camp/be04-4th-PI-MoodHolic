package akatsuki.moodholic.service;

import akatsuki.moodholic.domain.Diary;
import akatsuki.moodholic.domain.DiaryEmotion;
import akatsuki.moodholic.repository.DiaryEmotionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

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
    public SortedMap<Integer, Integer> getEmotionMap(List<Diary> diaryList) {
        SortedMap<Integer,Integer> returnValue= new TreeMap<>();
        diaryList.forEach(diary -> {
            DiaryEmotion diaryEmotion = diaryEmotionDAO.findByDiaryIdDiaryIdOrderByDiaryIdDateAsc(diary.getDiaryId());
            if(diaryEmotion != null && diaryEmotion.getDiaryId()!=null){
                returnValue.put(diaryEmotion.getDiaryId().getDiaryId(),diaryEmotion.getEmotionId());
            }
        });
        System.out.println("returnValue = " + returnValue);
        return returnValue;
    }

    @Override
    public SortedMap<String, Double> getEmotionDayMap(List<Diary> diaryList) {
        SortedMap<String,Double> returnValue= new TreeMap<>();
        diaryList.forEach(diary -> {
            DiaryEmotion diaryEmotion = diaryEmotionDAO.findByDiaryIdDiaryIdOrderByDiaryIdDateAsc(diary.getDiaryId());
            if(diaryEmotion.getDiaryId()!=null){
                returnValue.put(diaryEmotion.getDiaryId().getDate(), diaryEmotion.getEmotionId()*1.0);
            }
        });
        System.out.println("returnValue = " + returnValue);
        return returnValue;
    }
}
