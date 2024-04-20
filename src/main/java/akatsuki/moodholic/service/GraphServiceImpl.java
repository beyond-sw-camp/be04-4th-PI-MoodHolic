package akatsuki.moodholic.service;

import akatsuki.moodholic.domain.Diary;
import akatsuki.moodholic.domain.DiaryEmotion;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.TreeMap;

@Service
public class GraphServiceImpl implements GraphService{

    double sum;
    double cnt;
    String past;
    List<DiaryEmotion> emotionList;
    Map<String,Double> returnValue;

    private void init() {
        returnValue = new TreeMap<>();
        past= "";
        sum=0;
        cnt=0;
        emotionList = new ArrayList<>();
    }

     @Override
     public Map<String,Double> GetEmotionMonth(long memberId, List<Diary> diaryList,Map<Diary,Integer> memberEmotion){
         init();
         diaryList.forEach(diary->{
            String[] date = diary.getDate().split("-");
            String cmpDate = date[0]+"-"+date[1];
            compare(cmpDate, diary,memberEmotion);
        });
         if (!past.isEmpty()) {
             returnValue.put(past, Math.round((sum / cnt) * 100) / 100.0d);
         }
        return returnValue;
    }
    @Override
    public Map<String,Double> GetEmotionYear(long memberId, List<Diary> diaryList,Map<Diary,Integer> memberEmotion){
        init();
        diaryList.forEach(diary->{
            String[] date = diary.getDate().split("-");
            String cmpDate = date[0];
            compare(cmpDate,diary,memberEmotion);
        });
        if (!past.isEmpty()) {
            returnValue.put(past, Math.round((sum / cnt) * 100) / 100.0d);
        }
        return returnValue;
    }

    @Override
    public Map<String, Double> GetEmotionWeek(long memberId, List<Diary> diaryList,Map<Diary,Integer> memberEmotion) {
        init();
        diaryList.forEach(diary -> {
            String[] date = diary.getDate().split("-");
            int dayOfMonth = Integer.parseInt(date[2]);
            int weekOfMonth = (dayOfMonth - 1) / 7 + 1;
            String cmpDate = date[0] + "-" + date[1] + "-W" + weekOfMonth;
            compare(cmpDate, diary,memberEmotion);
        });
        if (!past.isEmpty()) {
            returnValue.put(past, Math.round((sum / cnt) * 100) / 100.0d);
        }
        return returnValue;
    }

    private void compare(String cmpDate, Diary diary,Map<Diary,Integer> memberEmotion) {
        if(memberEmotion.get(diary.getDiaryId())==null)
            return;
        int score = memberEmotion.get(diary.getDiaryId());

        if (past.equals(cmpDate)) {
            sum += score;
            cnt++;
            return;
        }
        if(!past.isEmpty()) {
            returnValue.put(past, Math.round((sum/cnt)*100)/100.0d);
        }
        this.past = cmpDate;
        sum = score;
        cnt = 1;

    }

}
