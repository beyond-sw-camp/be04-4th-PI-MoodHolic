package akatsuki.moodholic.dto;

import akatsuki.moodholic.domain.*;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
public class ResponseDiary {
    private Diary diary;
    private String emotion;
    private Food food;
    private Movie movie;
    private Music music;


    public ResponseDiary(Diary diary, String emotion, Food food, Movie movie, Music music) {
        this.diary = diary;
        this.emotion = emotion;
        this.food = food;
        this.movie = movie;
        this.music = music;
    }

}
