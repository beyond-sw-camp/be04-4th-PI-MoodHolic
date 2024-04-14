package akatsuki.moodholic.dto;

import lombok.Data;

import java.util.HashMap;

@Data
public class MemberMovieGenreRanking {
    private HashMap<String, Integer> movieRankings;
    private String topName;
    private int topCnt;

    public MemberMovieGenreRanking(HashMap<String, Integer> lists, int maximum, String name) {
        movieRankings=lists;
        topName=name;
        topCnt=maximum;
    }
}
