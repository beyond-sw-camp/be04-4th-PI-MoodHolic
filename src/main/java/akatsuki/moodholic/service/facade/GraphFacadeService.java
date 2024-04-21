package akatsuki.moodholic.service.facade;

import akatsuki.moodholic.domain.Diary;
import akatsuki.moodholic.service.DiaryEmotionService;
import akatsuki.moodholic.service.DiaryService;
import akatsuki.moodholic.service.GraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GraphFacadeService {
    GraphService graphService;
    DiaryService diaryService;
    DiaryEmotionService diaryEmotionService;

    @Autowired
    public GraphFacadeService(GraphService graphService, DiaryService diaryService, DiaryEmotionService diaryEmotionService) {
        this.graphService = graphService;
        this.diaryService = diaryService;
        this.diaryEmotionService = diaryEmotionService;
    }

    public Map<String, Double> GetEmotionMonth(long memberId) {
        Map<Diary,Integer> memberEmotion = diaryEmotionService.getEmotionMap(memberId);
        return graphService.GetEmotionMonth(memberEmotion);
    }

    public Map<String, Double> GetEmotionYear(long memberId) {
        Map<Diary,Integer> memberEmotion = diaryEmotionService.getEmotionMap(memberId);
        return graphService.GetEmotionYear(memberEmotion);
    }

    public Map<String, Double> GetEmotionWeek(long memberId) {
        Map<Diary,Integer> memberEmotion = diaryEmotionService.getEmotionMap(memberId);
        return graphService.GetEmotionWeek(memberEmotion);
    }

    public Map<String, Double> GetEmotionDay(long memberId) {
//        List<Diary> diaryList = diaryService.findAllByMemberOrderByDateAsc(memberId);
        Map<Diary,Integer> memberEmotion = diaryEmotionService.getEmotionMap(memberId);
        return graphService.GetEmotionDay(memberEmotion);
    }
}
