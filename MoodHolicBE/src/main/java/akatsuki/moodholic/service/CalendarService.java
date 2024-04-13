package akatsuki.moodholic.service;


import akatsuki.moodholic.domain.Diary;
import akatsuki.moodholic.dto.Calendar;

import java.util.HashMap;
import java.util.List;
import java.util.SortedMap;

public interface CalendarService {
    List<Calendar> getCalendarOfYear(List<Diary> diaryList, int year,SortedMap<Integer, Integer> emotions);

    List<Calendar> getCalendar(List<Diary> diaries, SortedMap<Integer, Integer> emotions);
}
