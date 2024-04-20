package akatsuki.moodholic.service;

import akatsuki.moodholic.domain.Diary;
import akatsuki.moodholic.domain.DiaryEmotion;
import akatsuki.moodholic.repository.DiaryEmotionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Service
public class DiaryEmotionServiceImpl implements DiaryEmotionService{
    DiaryEmotionDAO diaryEmotionDAO;

    @Autowired
    public DiaryEmotionServiceImpl(DiaryEmotionDAO diaryEmotionDAO) {
        this.diaryEmotionDAO = diaryEmotionDAO;
    }

    @Override
    public DiaryEmotion findEmotionByDiaryId(int diaryId){
        return diaryEmotionDAO.findByDiaryDiaryId(diaryId);
    }

    @Override
    public void saveDiaryEmotion(DiaryEmotion diaryEmotion){
        diaryEmotionDAO.save(diaryEmotion);
    }

    @Override
    public void delete(int diaryId){
        diaryEmotionDAO.deleteByDiaryDiaryId(diaryId);
    }

    @Override
    public Map<Diary, Integer> getEmotionMap(long memberId) {

        List<DiaryEmotion> diaryEmotionList =  diaryEmotionDAO.findByDiaryMemberMemberId(memberId);
        Map<Diary,Integer> returnValue = new TreeMap<>(diaryEmotionList.stream().collect(Collectors.toMap(DiaryEmotion::getDiary, DiaryEmotion::getEmotionId )));
//        diaryList.forEach(diary -> {
//            DiaryEmotion diaryEmotion = diaryEmotionDAO.findByDiaryDiaryIdOrderByDiaryDateAsc(diary.getDiaryId());
//            if(diaryEmotion!=null&&diaryEmotion.getDiary()!=null){
//                returnValue.put(diaryEmotion.getDiary().getDiaryId(),diaryEmotion.getEmotionId());
//            }
//        });

        return returnValue;
    }

    @Override
    public SortedMap<String, Double> getEmotionDayMap(List<Diary> diaryList) {
        SortedMap<String,Double> returnValue= new TreeMap<>();
        diaryList.forEach(diary -> {
            DiaryEmotion diaryEmotion = diaryEmotionDAO.findByDiaryDiaryIdOrderByDiaryDateAsc(diary.getDiaryId());
            if(diaryEmotion!=null&&diaryEmotion.getDiary()!=null){
                returnValue.put(diaryEmotion.getDiary().getDate(), diaryEmotion.getEmotionId()*1.0);
            }
        });
        return returnValue;
    }
}
