package akatsuki.moodholic.service;

import akatsuki.moodholic.domain.Diary;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

public interface GraphService {
    Map<String,Double> GetEmotionMonth(Map<Diary,Integer> memberEmotion);
    Map<String,Double> GetEmotionYear(Map<Diary,Integer> memberEmotion);

    Map<String,Double> GetEmotionWeek(Map<Diary,Integer> memberEmotion);

    Map<String, Double> GetEmotionDay(Map<Diary, Integer> memberEmotion);
}
