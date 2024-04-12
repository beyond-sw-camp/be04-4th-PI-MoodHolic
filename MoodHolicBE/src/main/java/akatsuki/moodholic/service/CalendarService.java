package akatsuki.moodholic.service;


import akatsuki.moodholic.domain.Diary;
import akatsuki.moodholic.dto.Calendar;

import java.util.HashMap;
import java.util.List;

public interface CalendarService {
    List<Calendar> getCalendarOfYear(List<Diary> diaryList, int year,HashMap<Integer, Integer> emotions);

    List<Calendar> getCalendar(List<Diary> diaries, HashMap<Integer, Integer> emotions);
}
