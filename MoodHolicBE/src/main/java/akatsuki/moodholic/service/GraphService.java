package akatsuki.moodholic.service;

import akatsuki.moodholic.domain.Diary;

import java.util.HashMap;
import java.util.List;
import java.util.SortedMap;

public interface GraphService {
    SortedMap<String,Double> GetEmotionMonth(long memberId, List<Diary> diaryList,SortedMap<Integer,Integer> memberEmotion );
    SortedMap<String,Double> GetEmotionYear(long memberId, List<Diary> diaryList,SortedMap<Integer,Integer> memberEmotion);

    SortedMap<String,Double> GetEmotionWeek(long memberId, List<Diary> diaryList,SortedMap<Integer,Integer> memberEmotion);

}
