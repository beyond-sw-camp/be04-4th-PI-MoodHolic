package akatsuki.moodholic.service;

import java.util.HashMap;

public interface GraphService {

    HashMap<String,Double> GetEmotionMonth(long memberId);
    HashMap<String,Double> GetEmotionYear(long memberId);

    HashMap<String,Double> GetEmotionWeek(long memberId);

}
