package akatsuki.moodholic.service.facade;

import akatsuki.moodholic.domain.Diary;
import akatsuki.moodholic.dto.Calendar;
import akatsuki.moodholic.service.CalendarService;
import akatsuki.moodholic.service.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalendarFacadeService {
    private final CalendarService calendarService;
    private final DiaryService diaryService;

    @Autowired
    public CalendarFacadeService(CalendarService calendarService, DiaryService diaryService) {
        this.calendarService = calendarService;
        this.diaryService = diaryService;
    }

    public List<Calendar> getCalendar(long memberId) {
        return calendarService.getCalendar(getDiaries(memberId));
    }
    public List<Calendar> getCalendarOfYear(long memberId, int year) {
        return calendarService.getCalendarOfYear(getDiaries(memberId),year);
    }

    private List<Diary> getDiaries(long memberId) {
        List<Diary> diaryList = diaryService.getMemberDiaries(memberId);
        return diaryList;
    }

}
