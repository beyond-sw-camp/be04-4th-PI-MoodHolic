package akatsuki.moodholic.controller;

import akatsuki.moodholic.dto.Calendar;
import akatsuki.moodholic.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/graph")
public class CalendarController {
    CalendarService calendarService;

    @Autowired
    public CalendarController(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    @GetMapping("/year/{memberId}")
    public ResponseEntity<List<Calendar>> getYearEmotion(@PathVariable long memberId){
//        List<Calendar> returnValue = calendarService.getCalendar(memberId);
//        return ResponseEntity.ok().body(returnValue);
        return null;
    }

}
