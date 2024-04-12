package akatsuki.moodholic.service;

import akatsuki.moodholic.domain.Diary;
import akatsuki.moodholic.dto.Calendar;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class CalendarServiceImpl implements CalendarService{

    @Override
    public List<Calendar> getCalendar(List<Diary> diaryList, HashMap<Integer, Integer> emotions) {
        List<Calendar> calendar = new ArrayList<>();
        diaryList.forEach(diary ->{
            String[] DATE = diary.getDate().split("-");
            String summary = diary.getSummary();
            String comment = diary.getContent();
            String emotion_name;
            int emotion_score= emotions.get(diary.getDiaryId());
            if (emotion_score < 4)  emotion_name = "나쁨";
            else if(emotion_score<7) emotion_name ="보통";
            else emotion_name ="좋음";

            if(summary.length()>5){
                summary=summary.substring(0,5)+"...";
            }
            if(comment.length()>12){
                comment=comment.substring(0,12) +"...";
            }
            calendar.add(new Calendar(diary.getDiaryId(),diary.getStatus(),emotion_name, DATE[0],DATE[1],DATE[2],summary, comment ));
        });
        return calendar;
    }

    public List<Calendar> getCalendarOfYear(List<Diary> diaryList, int year, HashMap<Integer, Integer> emotions){
        List<Calendar> calendar = new ArrayList<>();
        String YEAR = year+"";
        diaryList.forEach(diary ->{
            String[] cmpYear= diary.getDate().split("-");
            if(YEAR.equals(cmpYear[0])){
                String[] DATE = diary.getDate().split("-");
                String summary = diary.getSummary();
                String comment = diary.getContent();
                String emotion_name;
                int emotion_score= emotions.get(diary.getDiaryId());
                if (emotion_score < 4)  emotion_name = "나쁨";
                else if(emotion_score<7) emotion_name ="보통";
                else emotion_name ="좋음";
                if(summary.length()>5){
                    summary=summary.substring(0,5)+"...";
                }
                if(comment.length()>12){
                    comment=comment.substring(0,12) +"...";
                }
                calendar.add(new Calendar(diary.getDiaryId(),diary.getStatus(),emotion_name, DATE[0],DATE[1],DATE[2],summary, comment ));
            }
        });

        return calendar;
    }


}

