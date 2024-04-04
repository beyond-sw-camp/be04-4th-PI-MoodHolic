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

    public ResponseDiary(Diary diary, Emotion emotion, Food food, Movie movie, Music music) {
        this.diary = diary;
        insertEmotion(emotion);
        insertFood(food);
        insertMovie(movie);
        insertMusic(music);
    }

    private void insertMusic(Music music) {
        if(music !=null){
            this.music= music;
        }
    }

    private void insertMovie(Movie movie) {
        if(movie !=null){
            this.movie = movie;
        }
    }

    private void insertFood(Food food) {
        if(food !=null){
            this.food= food;
        }
    }

    private void insertEmotion(Emotion emotion) {
        if(emotion !=null) {
            if (emotion.getEmotionScore() < 4) this.emotion = "나쁨";
            else if(emotion.getEmotionScore()<7) this.emotion="보통";
            else this.emotion="좋음";
        }
    }
}
