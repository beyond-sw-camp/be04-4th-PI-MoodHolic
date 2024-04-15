package akatsuki.moodholic.service.facade;

import akatsuki.moodholic.domain.Diary;
import akatsuki.moodholic.dto.Calendar;
import akatsuki.moodholic.repository.DiaryDAO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CalendarFacadeServiceTest {
    @Autowired
    CalendarFacadeService calendarFacadeService;
    @Autowired
    DiaryDAO diaryDAO;

    long memberId;

    @Test
    @DisplayName("멤버의 달력 리스트 출력")
    void getCalendar(){
        /*given*/
        memberId=1;
        /*when*/
        List<Calendar> returnValue =calendarFacadeService.getCalendar(memberId);
        /*then*/
        returnValue.forEach(calendar -> {
            Diary diary =diaryDAO.findById(calendar.getDiaryId()).orElseThrow();
            // 불러온 다이어리의 멤버 아이디와 기존 멤버 아이디 일치하는지 검사
            assertEquals(diary.getMember().getMemberId(),memberId);
        });
    }

    @Test
    @DisplayName("멤버의 달력 년도에 대한 리스트 출력")
    void getCalendarOfYear(){
        /*given*/
        memberId=1;
        int year= 2024;
        /*when*/
        List<Calendar> returnValue =calendarFacadeService.getCalendarOfYear(memberId,year);
        /*then*/
        returnValue.forEach(calendar -> {
            // 불러온 달력의 년도가 모두 2024인지 확인
            assertEquals(year,Integer.parseInt(calendar.getYear()));

        });
    }

}