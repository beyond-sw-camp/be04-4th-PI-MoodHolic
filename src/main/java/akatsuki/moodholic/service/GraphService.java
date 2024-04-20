package akatsuki.moodholic.service;

import akatsuki.moodholic.domain.Diary;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

public interface GraphService {
    Map<String,Double> GetEmotionMonth(long memberId, List<Diary> diaryList,Map<Diary,Integer> memberEmotion );
    Map<String,Double> GetEmotionYear(long memberId, List<Diary> diaryList, Map<Diary,Integer> memberEmotion);

    Map<String,Double> GetEmotionWeek(long memberId, List<Diary> diaryList,Map<Diary,Integer> memberEmotion);

}
