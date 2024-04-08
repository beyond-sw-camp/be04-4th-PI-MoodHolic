package akatsuki.moodholic.service;


import akatsuki.moodholic.dto.Calendar;

import java.util.List;

public interface CalendarService {
    List<Calendar> getCalendar(long memberId);

    List<Calendar> getCalendarOfYear(long memberId, int year);
}
