package akatsuki.moodholic.controller;

import akatsuki.moodholic.dto.Calendar;
import akatsuki.moodholic.service.facade.CalendarFacadeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/calendar")
@Tag(name = "캘린더 관련 컨트롤러", description = "달력 관련 로직을 처리하기 위한 컨트롤러 입니다.")
public class CalendarController {

    private final CalendarFacadeService calendarFacadeService;

    @Autowired
    public CalendarController(CalendarFacadeService calendarFacadeService) {
        this.calendarFacadeService = calendarFacadeService;
    }

    @GetMapping("/{memberId}")
    @Operation(summary = "달력 리스트 출력", description = "맴버가 작성한 기록이 있는 모든 다이어리 날짜와 저장상태들을 반환합니다." +
            "\n만약 임시저장되어 있는 다이어리도 있을 수 있기 때문에 상태값도 포함하여 반환합니다.")
    public ResponseEntity<List<Calendar>> getCalendar(@PathVariable long memberId){
        List<Calendar> returnValue = calendarFacadeService.getCalendar(memberId);
        return ResponseEntity.ok().body(returnValue);
    }

    @GetMapping("/year/{memberId}")
    @Operation(summary = "년도에 따른 달력 리스트 출력", description = "맴버가 작성한 기록이 있는 모든 다이어리 날짜와 저장상태들을 요청한 년도에 따라 반환합니다." +
            "\n만약 임시저장되어 있는 다이어리도 있을 수 있기 때문에 상태값도 포함하여 반환합니다.")
    public ResponseEntity<List<Calendar>> getCalendarOfYear(@PathVariable long memberId, @RequestParam(name = "year") int year){
        List<Calendar> returnValue = calendarFacadeService.getCalendarOfYear(memberId,year);
        return ResponseEntity.ok().body(returnValue);
    }

}
