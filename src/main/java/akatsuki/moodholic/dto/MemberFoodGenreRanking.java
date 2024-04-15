package akatsuki.moodholic.dto;

import lombok.Data;

import java.util.HashMap;

@Data
public class MemberFoodGenreRanking {
    private HashMap<String, Integer> foodRankings;
    private String topName;
    private int topCnt;

    public MemberFoodGenreRanking(HashMap<String, Integer> lists, int maximum, String name) {
        foodRankings=lists;
        topName=name;
        topCnt=maximum;
    }

}
