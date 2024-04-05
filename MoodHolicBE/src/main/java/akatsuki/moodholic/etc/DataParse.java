package akatsuki.moodholic.etc;

import akatsuki.moodholic.domain.Comment;
import akatsuki.moodholic.domain.Food;
import akatsuki.moodholic.domain.Movie;
import akatsuki.moodholic.domain.Music;
import lombok.Data;

import java.util.ArrayList;

@Data
public class DataParse {

    int emotionScore;
    Comment comment = new Comment();
    Movie movie = new Movie();
    Music music = new Music();
    Food food = new Food();

    public DataParse(String text) {
        parsing(text);
    }

    public void parsing(String input) {

        String[] lines = input.split("\n");
        for (String line : lines) {
            line = line.replace("\"","");
            int colonIndex = line.indexOf(": ");
            if (colonIndex != -1) {
                String itemName = line.substring(0, colonIndex);
                String itemValue = line.substring(colonIndex + 2);
                findItem(itemName, itemValue);
            }
        }
    }

    private void findItem(String itemName, String itemValue) {
        switch (itemName) {
            case "분석 점수(1~10)":
                emotionScore = Integer.parseInt(itemValue);
                break;
            case "조언 및 인용구 한 줄":
                comment.setCommentContent(itemValue);
                break;
            case "추천 영화(영화이름,장르)":
                String[] movieInfo = itemValue.split(" - ");
                movie.setMovieName(movieInfo[0]);
                movie.setMovieGenre(movieInfo[1]);
                break;
            case "추천 음악(음악이름,가수,장르)":
                String[] musicInfo = itemValue.split(" - ");
                music.setMusicName(musicInfo[0]);
                music.setSinger(musicInfo[1]);
                music.setMusicGenre(musicInfo[2]);
                break;
            case "추천 음식(음식이름,메뉴카테고리(한식,양식,중식,일식,아시안),맵기(0~3))":
                String[] foodInfo = itemValue.split(" - ");
                food.setFoodName(foodInfo[0]);
                food.setFoodCategory(FOOD_CATEGORY.valueOf(foodInfo[1]));
                food.setFoodSpicy(Integer.parseInt(foodInfo[2]));
                break;
        }
    }


}
