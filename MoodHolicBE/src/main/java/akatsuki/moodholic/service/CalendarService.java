package akatsuki.moodholic.service;


import akatsuki.moodholic.domain.Diary;
import akatsuki.moodholic.dto.Calendar;

import java.util.List;

public interface CalendarService {
    List<Calendar> getCalendar(List<Diary> diaryList);

    List<Calendar> getCalendarOfYear(List<Diary> diaryList, int year);
}
