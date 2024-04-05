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

//    @Override
//    public List<Calendar> getCalendar(long memberId) {
////        List<Diary> diaryList = diaryDAO.findAllByMemberMemberIdByDate(memberId);
//        List<Calendar> calendar = new ArrayList<>();
////        diaryList.forEach(diary ->{
////            calendar.add(new Calendar(diary.getStatus(),diary.getDate()));
////        });
//        return calendar;
//    }
}
