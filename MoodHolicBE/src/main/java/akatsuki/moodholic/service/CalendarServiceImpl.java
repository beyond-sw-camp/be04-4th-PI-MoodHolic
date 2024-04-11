package akatsuki.moodholic.service;

import akatsuki.moodholic.domain.Diary;
import akatsuki.moodholic.dto.Calendar;
import akatsuki.moodholic.repository.DiaryDAO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CalendarServiceImpl implements CalendarService{

    @Override
    public List<Calendar> getCalendar(List<Diary> diaryList) {
        List<Calendar> calendar = new ArrayList<>();
        diaryList.forEach(diary ->{
            String[] DATE = diary.getDate().split("-");
            calendar.add(new Calendar(diary.getStatus(),DATE[0],DATE[1],DATE[2]));
        });
        return calendar;
    }

    public List<Calendar> getCalendarOfYear(List<Diary> diaryList, int year){
        List<Calendar> calendar = new ArrayList<>();
        String YEAR = year+"";
        diaryList.forEach(diary ->{
            String[] cmpYear= diary.getDate().split("-");
            if(YEAR.equals(cmpYear[0])){
                String[] DATE = diary.getDate().split("-");
                calendar.add(new Calendar(diary.getStatus(),DATE[0],DATE[1],DATE[2]));
            }
        });

        return calendar;
    }
}

