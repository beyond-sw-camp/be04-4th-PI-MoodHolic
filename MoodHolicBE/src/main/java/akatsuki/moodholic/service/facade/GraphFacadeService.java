package akatsuki.moodholic.service.facade;

import akatsuki.moodholic.domain.Diary;
import akatsuki.moodholic.service.DiaryEmotionService;
import akatsuki.moodholic.service.DiaryService;
import akatsuki.moodholic.service.GraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.SortedMap;

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


    public SortedMap<String, Double> GetEmotionMonth(long memberId) {
        List<Diary> diaryList = diaryService.findAllByMemberOrderByDateAsc(memberId);
        SortedMap<Integer,Integer> memberEmotion = diaryEmotionService.getEmotionMap(diaryList);
        return graphService.GetEmotionMonth(memberId,diaryList,memberEmotion);
    }

    public SortedMap<String, Double> GetEmotionYear(long memberId) {
        List<Diary> diaryList = diaryService.findAllByMemberOrderByDateAsc(memberId);
        SortedMap<Integer,Integer> memberEmotion = diaryEmotionService.getEmotionMap(diaryList);
        return graphService.GetEmotionYear(memberId,diaryList,memberEmotion);
    }

    public SortedMap<String, Double> GetEmotionWeek(long memberId) {
        List<Diary> diaryList = diaryService.findAllByMemberOrderByDateAsc(memberId);
        SortedMap<Integer,Integer> memberEmotion = diaryEmotionService.getEmotionMap(diaryList);
        return graphService.GetEmotionWeek(memberId,diaryList,memberEmotion);
    }

    public SortedMap<String, Double> GetEmotionDay(long memberId) {
        List<Diary> diaryList = diaryService.findAllByMemberOrderByDateAsc(memberId);
        SortedMap<String,Double> memberEmotion = diaryEmotionService.getEmotionDayMap(diaryList);

        return memberEmotion;
    }
}
