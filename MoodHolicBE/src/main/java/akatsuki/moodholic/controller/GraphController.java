package akatsuki.moodholic.controller;

import akatsuki.moodholic.service.GraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/graph")
public class GraphController {

    GraphService graphService;

    @Autowired
    public GraphController(GraphService graphService) {
        this.graphService = graphService;
    }

    @GetMapping("/month/{memberId}")
    public ResponseEntity<HashMap<String,Double>> getMonth(@PathVariable long memberId){
        HashMap<String,Double> returnValue = graphService.GetEmotionMonth(memberId);
        return ResponseEntity.ok().body(returnValue);
    }

    @GetMapping("/year/{memberId}")
    public ResponseEntity<HashMap<String,Double>> getYear(@PathVariable long memberId){
        HashMap<String,Double> returnValue = graphService.GetEmotionYear(memberId);
        return ResponseEntity.ok().body(returnValue);
    }
}
