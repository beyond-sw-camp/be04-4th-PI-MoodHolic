package akatsuki.moodholic.dto;

import lombok.Data;

import java.util.HashMap;

@Data
public class MemberMusicGenreRanking {
    private HashMap<String, Integer> musicRankings;
    private String topName;
    private int topCnt;

    public MemberMusicGenreRanking(HashMap<String, Integer> lists, int maximum, String name) {
        musicRankings=lists;
        topName=name;
        topCnt=maximum;
    }
}
