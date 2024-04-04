package akatsuki.moodholic.etc;

import java.util.ArrayList;

public class DataParse {

    int emotionScore;
    String advice = "";
    ArrayList<String> movies = new ArrayList<>();
    ArrayList<String> musics = new ArrayList<>();
    ArrayList<String> foods = new ArrayList<>();

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

        // 결과 출력
        System.out.println("점수: " + emotionScore+ " 조언: " + advice
                         + " 추천 영화: " + movies + " 추천 음악: " + musics  +" 추천 음식: " + foods);
    }

    private void findItem(String itemName, String itemValue) {
        switch (itemName) {
            case "분석 점수(1~10)":
                emotionScore = Integer.parseInt(itemValue);
                break;
            case "조언 및 인용구 한 줄":
                advice = itemValue;
                break;
            case "추천 영화":
                movies.add(itemValue);
                break;
            case "추천 음악":
                musics.add(itemValue);
                break;
            case "추천 음식":
                foods.add(itemValue);
                break;
        }
    }

}
