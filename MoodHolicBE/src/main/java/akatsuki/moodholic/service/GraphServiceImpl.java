package akatsuki.moodholic.service;

import akatsuki.moodholic.domain.Diary;
import akatsuki.moodholic.domain.DiaryEmotion;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class GraphServiceImpl implements GraphService{

    double sum;
    double cnt;
    String past;
    List<DiaryEmotion> emotionList;
    HashMap<String,Double> returnValue;

    private void init() {
        returnValue = new HashMap<>();
        past= "";
        sum=0;
        cnt=0;
        emotionList = new ArrayList<>();
    }

     @Override
     public HashMap<String,Double> GetEmotionMonth(long memberId, List<Diary> diaryList,HashMap<Integer,Integer> memberEmotion){
         init();
         diaryList.forEach(diary->{
            String[] date = diary.getDate().split("-");
            String cmpDate = date[0]+"-"+date[1];
            compare(cmpDate, diary,memberEmotion);
        });
        return returnValue;
    }
    @Override
    public HashMap<String,Double> GetEmotionYear(long memberId, List<Diary> diaryList,HashMap<Integer,Integer> memberEmotion){
        init();
        diaryList.forEach(diary->{
            String[] date = diary.getDate().split("-");
            String cmpDate = date[0];
            compare(cmpDate,diary,memberEmotion);
        });
        return returnValue;
    }

    @Override
    public HashMap<String, Double> GetEmotionWeek(long memberId, List<Diary> diaryList,HashMap<Integer,Integer> memberEmotion) {
        init();
        diaryList.forEach(diary -> {
            String[] date = diary.getDate().split("-");
            int dayOfMonth = Integer.parseInt(date[2]);
            int weekOfMonth = (dayOfMonth - 1) / 7 + 1;
            String cmpDate = date[0] + "-" + date[1] + "-W" + weekOfMonth;
            compare(cmpDate, diary,memberEmotion);
        });
        // 마지막 주 처리
        if (!past.isEmpty()) {
            returnValue.put(past, Math.round((sum / cnt) * 100) / 100.0d);
        }
        return returnValue;
    }

    private void compare(String cmpDate, Diary diary,HashMap<Integer,Integer> memberEmotion) {

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
