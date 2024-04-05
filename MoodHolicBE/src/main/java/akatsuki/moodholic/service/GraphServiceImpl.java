package akatsuki.moodholic.service;

import akatsuki.moodholic.domain.Diary;
import akatsuki.moodholic.domain.DiaryEmotion;
import akatsuki.moodholic.domain.Emotion;
import akatsuki.moodholic.repository.DiaryDAO;
import akatsuki.moodholic.repository.DiaryEmotionDAO;
import akatsuki.moodholic.repository.MemberDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class GraphServiceImpl implements GraphService{

    DiaryDAO diaryDAO;
    DiaryEmotionDAO diaryEmotionDAO;
    double sum =0;
    double cnt =0;
    String past;
    List<DiaryEmotion> emotionList;
    List<Diary> diaryList;
    HashMap<String,Double> returnValue;

    @Autowired
    public GraphServiceImpl(DiaryEmotionDAO diaryEmotionDAO,DiaryDAO diaryDAO) {
        this.diaryEmotionDAO = diaryEmotionDAO;
        this.diaryDAO = diaryDAO;
    }
    private void init(long memberId) {
        returnValue = new HashMap<>();
        past= new String();
        sum=0;
        cnt=0;
        diaryList = diaryDAO.findAllByMemberMemberIdOrderByDateAsc(memberId);
        emotionList = new ArrayList<>();
    }

     @Override
     public HashMap<String,Double> GetEmotionMonth(long memberId){
         init(memberId);
         diaryList.forEach(diary->{
            String[] date = diary.getDate().split("-");
            String cmpDate = date[0]+"-"+date[1];
            System.out.println("cmpDate = " + cmpDate);
            DiaryEmotion score = diaryEmotionDAO.findByDiaryIdDiaryId(diary.getDiaryId());
            if(score!=null) {
                if (past.equals(cmpDate)) {
                    sum += score.getEmotionId();
                    cnt++;
                } else {
                    returnValue.put(past, (sum / cnt));
                    this.past = cmpDate;
                    sum = score.getEmotionId();
                    cnt = 1;
                }
            }
        });
        return returnValue;
    }


    @Override
    public HashMap<String,Double> GetEmotionYear(long memberId){
        init(memberId);

        diaryList.forEach(diary->{
            String[] date = diary.getDate().split("-");
            System.out.println("date = " + date[0]);
            String cmpDate = date[0];
            DiaryEmotion score = diaryEmotionDAO.findByDiaryIdDiaryId(diary.getDiaryId());
            if(score!=null) {
                if (past.equals(cmpDate)) {
                    sum += score.getEmotionId();
                    cnt++;
                } else {
                    returnValue.put(past, (sum / cnt));
                    this.past = cmpDate;
                    sum = score.getEmotionId();
                    cnt = 1;
                }
            }
        });
        return returnValue;
    }

}
