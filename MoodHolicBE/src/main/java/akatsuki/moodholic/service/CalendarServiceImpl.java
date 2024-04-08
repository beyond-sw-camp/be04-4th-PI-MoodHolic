package akatsuki.moodholic.service;

import akatsuki.moodholic.domain.Diary;
import akatsuki.moodholic.dto.Calendar;
import akatsuki.moodholic.repository.DiaryDAO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CalendarServiceImpl implements CalendarService{
    DiaryDAO diaryDAO;

    public CalendarServiceImpl(DiaryDAO diaryDAO) {
        this.diaryDAO = diaryDAO;
    }

    @Override
    public List<Calendar> getCalendar(long memberId) {
        List<Diary> diaryList = diaryDAO.findAllByMemberMemberIdOrderByDateAsc(memberId);
        List<Calendar> calendar = new ArrayList<>();
        diaryList.forEach(diary ->{
            calendar.add(new Calendar(diary.getStatus(),diary.getDate()));
        });
        return calendar;
    }
    public List<Calendar> getCalendarOfYear(long memberId, int year){
        List<Diary> diaryList = diaryDAO.findAllByMemberMemberIdOrderByDateAsc(memberId);
        List<Calendar> calendar = new ArrayList<>();
        String YEAR = year+"";
        diaryList.forEach(diary ->{
            String[] cmpYear= diary.getDate().split("-");
            if(YEAR.equals(cmpYear[0])){
                calendar.add(new Calendar(diary.getStatus(),diary.getDate()));
            }
        });
        System.out.println("calendar = " + calendar);
        return calendar;
    }
}

