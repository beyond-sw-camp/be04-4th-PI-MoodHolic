package akatsuki.moodholic.service;

import akatsuki.moodholic.domain.Diary;
import akatsuki.moodholic.dto.Calendar;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CalendarServiceImpl implements CalendarService{

    @Override
    public List<Calendar> getCalendar(Map<Diary, Integer> emotions) {
        List<Calendar> calendar = new ArrayList<>();
        emotions.forEach((diary,emotionScore)->{
            addEmotionToCalendar(diary, calendar);
        });
        return calendar;
    }

    private static void addEmotionToCalendar(Diary diary, List<Calendar> calendar) {
        String[] DATE = diary.getDate().split("-");
        String summary = diary.getSummary();
        String comment = diary.getContent();
        String emotion_name;
        int emotion_score = diary.getDiaryId();

        if (emotion_score < 4) emotion_name = "나쁨";
        else if (emotion_score < 7) emotion_name = "보통";
        else emotion_name = "좋음";

        if (summary.length() > 5) {
            summary = summary.substring(0, 5) + "...";
        }
        if (comment.length() > 12) {
            comment = comment.substring(0, 12) + "...";
        }
        calendar.add(new Calendar(diary.getDiaryId(), diary.getStatus(), emotion_name, DATE[0], DATE[1], DATE[2], summary, comment));
    }

    public List<Calendar> getCalendarOfYear(int year, Map<Diary, Integer> emotions){
        List<Calendar> calendar = new ArrayList<>();
        String YEAR = year+"";
        emotions.forEach((diary,emotionScore)->{
            String[] cmpYear= diary.getDate().split("-");
            if(YEAR.equals(cmpYear[0]))
                addEmotionToCalendar(diary,calendar);
        });
        return calendar;
    }


}

