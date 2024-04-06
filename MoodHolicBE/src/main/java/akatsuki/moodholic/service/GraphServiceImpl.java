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
    double sum;
    double cnt;
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
            compare(cmpDate, diary);
        });
        return returnValue;
    }
    @Override
    public HashMap<String,Double> GetEmotionYear(long memberId){
        init(memberId);
        diaryList.forEach(diary->{
            String[] date = diary.getDate().split("-");
            String cmpDate = date[0];
            compare(cmpDate,diary);
        });
        return returnValue;
    }

    private void compare(String cmpDate, Diary diary) {
        DiaryEmotion score = diaryEmotionDAO.findByDiaryIdDiaryId(diary.getDiaryId());
        if(score!=null) {
            if (past.equals(cmpDate)) {
                sum += score.getEmotionId();
                cnt++;
                return;
            }
            if(!past.equals("")) {
                returnValue.put(past, Math.round((sum/cnt)*100)/100.0d);
            }
            this.past = cmpDate;
            sum = score.getEmotionId();
            cnt = 1;
        }
    }

}
