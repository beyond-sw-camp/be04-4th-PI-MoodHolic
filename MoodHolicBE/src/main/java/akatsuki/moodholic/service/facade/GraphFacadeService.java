package akatsuki.moodholic.service.facade;

import akatsuki.moodholic.domain.Diary;
import akatsuki.moodholic.service.DiaryEmotionService;
import akatsuki.moodholic.service.DiaryService;
import akatsuki.moodholic.service.GraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

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


    public HashMap<String, Double> GetEmotionMonth(long memberId) {
        List<Diary> diaryList = diaryService.findAllByMemberOrderByDateAsc(memberId);
        HashMap<Integer,Integer> memberEmotion = diaryEmotionService.getEmotionMap(diaryList);
        return graphService.GetEmotionMonth(memberId,diaryList,memberEmotion);
    }

    public HashMap<String, Double> GetEmotionYear(long memberId) {
        List<Diary> diaryList = diaryService.findAllByMemberOrderByDateAsc(memberId);
        HashMap<Integer,Integer> memberEmotion = diaryEmotionService.getEmotionMap(diaryList);
        return graphService.GetEmotionYear(memberId,diaryList,memberEmotion);
    }

    public HashMap<String, Double> GetEmotionWeek(long memberId) {
        List<Diary> diaryList = diaryService.findAllByMemberOrderByDateAsc(memberId);
        HashMap<Integer,Integer> memberEmotion = diaryEmotionService.getEmotionMap(diaryList);
        return graphService.GetEmotionWeek(memberId,diaryList,memberEmotion);
    }
}
