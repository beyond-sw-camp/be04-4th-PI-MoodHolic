package akatsuki.moodholic.service;


import akatsuki.moodholic.domain.Diary;
import akatsuki.moodholic.dto.Calendar;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

public interface CalendarService {
    List<Calendar> getCalendarOfYear(int year, Map<Diary, Integer> emotions);

    List<Calendar> getCalendar(Map<Diary, Integer> emotions);
}
