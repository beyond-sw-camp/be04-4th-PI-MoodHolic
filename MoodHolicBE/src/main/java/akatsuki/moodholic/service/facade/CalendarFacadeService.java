package akatsuki.moodholic.service.facade;

import akatsuki.moodholic.domain.Diary;
import akatsuki.moodholic.dto.Calendar;
import akatsuki.moodholic.service.CalendarService;
import akatsuki.moodholic.service.DiaryEmotionService;
import akatsuki.moodholic.service.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class CalendarFacadeService {
    private final CalendarService calendarService;
    private final DiaryService diaryService;
    private final DiaryEmotionService diaryEmotionService;

    @Autowired
    public CalendarFacadeService(CalendarService calendarService, DiaryService diaryService, DiaryEmotionService diaryEmotionService) {
        this.calendarService = calendarService;
        this.diaryService = diaryService;
        this.diaryEmotionService = diaryEmotionService;
    }

    public List<Calendar> getCalendar(long memberId) {
        List<Diary> diaries = getDiaries(memberId);
        HashMap<Integer, Integer> emotions = getIntegerIntegerHashMap(diaries);
        return calendarService.getCalendar(diaries,emotions);
    }
    public List<Calendar> getCalendarOfYear(long memberId, int year) {
        List<Diary> diaries = getDiaries(memberId);
        HashMap<Integer, Integer> emotions = getIntegerIntegerHashMap(diaries);
        return calendarService.getCalendarOfYear(getDiaries(memberId),year,emotions);
    }

    private HashMap<Integer, Integer> getIntegerIntegerHashMap(List<Diary> diaries) {
        HashMap<Integer,Integer> emotions = diaryEmotionService.getEmotionMap(diaries);
        return emotions;
    }

    private List<Diary> getDiaries(long memberId) {
        return diaryService.getMemberDiaries(memberId);
    }

}
