package akatsuki.moodholic.controller;

import akatsuki.moodholic.service.GraphService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/graph")
@Tag(name = "그래프 관련 컨트롤러", description = "주, 월, 년 단위의 기분 점수를 수치화하기 위한 컨트롤러 입니다.")
public class GraphController {

    GraphService graphService;

    @Autowired
    public GraphController(GraphService graphService) {
        this.graphService = graphService;
    }

    @GetMapping("/month/{memberId}")
    @Operation(summary = "월 단위 기분 통계", description = "멤버의 월 단위로 기분 평균을 수치화하여 반환합니다.")
    public ResponseEntity<HashMap<String,Double>> getMonth(@PathVariable long memberId){
        HashMap<String,Double> returnValue = graphService.GetEmotionMonth(memberId);
        return ResponseEntity.ok().body(returnValue);
    }

    @GetMapping("/year/{memberId}")
    @Operation(summary = "년 단위 기분 통계", description = "멤버의 년 단위로 기분 평균을 수치화하여 반환합니다.")
    public ResponseEntity<HashMap<String,Double>> getYear(@PathVariable long memberId){
        HashMap<String,Double> returnValue = graphService.GetEmotionYear(memberId);
        return ResponseEntity.ok().body(returnValue);
    }


}
