package akatsuki.moodholic.service;

import akatsuki.moodholic.domain.Diary;

import java.util.HashMap;
import java.util.List;

public interface GraphService {

    HashMap<String,Double> GetEmotionMonth(long memberId, List<Diary> diaryList,HashMap<Integer,Integer> memberEmotion );
    HashMap<String,Double> GetEmotionYear(long memberId, List<Diary> diaryList,HashMap<Integer,Integer> memberEmotion);

    HashMap<String,Double> GetEmotionWeek(long memberId, List<Diary> diaryList,HashMap<Integer,Integer> memberEmotion);

}
