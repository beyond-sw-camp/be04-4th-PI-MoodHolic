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
import java.util.Map;
import java.util.SortedMap;

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
        Map<Diary, Integer> emotions = getIntegerIntegerHashMap(memberId);
        return calendarService.getCalendar(emotions);
    }
    public List<Calendar> getCalendarOfYear(long memberId, int year) {
        Map<Diary, Integer> emotions = getIntegerIntegerHashMap(memberId);
        return calendarService.getCalendarOfYear(year,emotions);
    }

    private Map<Diary, Integer> getIntegerIntegerHashMap( long memberId) {
        Map<Diary,Integer> emotions = diaryEmotionService.getEmotionMap(memberId);
        return emotions;
    }
}
