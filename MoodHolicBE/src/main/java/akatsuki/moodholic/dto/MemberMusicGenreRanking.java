package akatsuki.moodholic.dto;

import lombok.Data;

import java.util.HashMap;

@Data
public class MemberMusicGenreRanking {
    private HashMap<String, Integer> movieRankings;
    private String topName;
    private int topCnt;

    public MemberMusicGenreRanking(HashMap<String, Integer> lists, int maximum, String name) {
        movieRankings=lists;
        topName=name;
        topCnt=maximum;
    }
}
