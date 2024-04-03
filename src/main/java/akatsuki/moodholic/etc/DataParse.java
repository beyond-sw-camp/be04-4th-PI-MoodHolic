package akatsuki.moodholic.etc;

import java.util.ArrayList;

public class DataParse {

    int emotionScore;
    String emotion = "";
    String advice = "";
    ArrayList<String> movies = new ArrayList<>();
    ArrayList<String> musics = new ArrayList<>();
    ArrayList<String> foods = new ArrayList<>();

    public void parsing(String input) {
//        String input = "분석 점수: 3\n" +
//                "추정된 기분: 나쁨\n" +
//                "조언 및 인용구 한 줄: \"마음의 하루가 언제나 행복하게 채워지는 것은\n" +
//                "                      아니에요. 오늘의 우울함도 잠시일 뿐입니다.\"\n" +
//                "추천 영화: \"인사 이야기\" - 이정배 - 로맨스\n" +
//                "추천 음악: \"봄날\" - 방탄소년단 - 힙합\n" +
//                "추천 음식: 된장찌개 - 한식 - 1";

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
        System.out.println("점수: " + emotionScore+ " 기분: " + emotion+ " 조언: " + advice
                         + " 추천 영화: " + movies + " 추천 음악: " + musics  +" 추천 음식: " + foods);
    }

    private void findItem(String itemName, String itemValue) {
        switch (itemName) {
            case "분석 점수":
                emotionScore = Integer.parseInt(itemValue);
                break;
            case "추정된 기분":
                emotion = itemValue;
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
