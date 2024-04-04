package akatsuki.moodholic.service;

import akatsuki.moodholic.domain.*;
import akatsuki.moodholic.dto.ResponseDiary;
import akatsuki.moodholic.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiaryService {
    private MemberDAO memberDAO;
    private DiaryDAO diaryDAO;
    private EmotionDAO emotionDAO;
    private FoodDAO foodDAO;
    private MovieDAO movieDAO;
    private MusicDAO musicDAO;
    private DiaryEmotionDAO diaryEmotionDAO;
    private DiaryFoodDAO diaryFoodDAO;
    private DiaryMovieDAO diaryMovieDAO;
    private DiaryMusicDAO diaryMusicDAO;
    @Autowired
    public DiaryService(MemberDAO memberDAO, DiaryDAO diaryDAO, EmotionDAO emotionDAO, FoodDAO foodDAO, MovieDAO movieDAO, MusicDAO musicDAO, DiaryEmotionDAO diaryEmotionDAO, DiaryFoodDAO diaryFoodDAO, DiaryMovieDAO diaryMovieDAO, DiaryMusicDAO diaryMusicDAO) {
        this.memberDAO = memberDAO;
        this.diaryDAO = diaryDAO;
        this.emotionDAO = emotionDAO;
        this.foodDAO = foodDAO;
        this.movieDAO = movieDAO;
        this.musicDAO = musicDAO;
        this.diaryEmotionDAO = diaryEmotionDAO;
        this.diaryFoodDAO = diaryFoodDAO;
        this.diaryMovieDAO = diaryMovieDAO;
        this.diaryMusicDAO = diaryMusicDAO;
    }

    public ResponseDiary getDiary(int diaryId) {
        Diary diary = diaryDAO.findById(diaryId).orElseThrow();
        // emotion, food, movie, music,
        DiaryFood food= diaryFoodDAO.findByDiaryIdDiaryId(diaryId);
        DiaryEmotion emotion = diaryEmotionDAO.findByDiaryIdDiaryId(diaryId);
        DiaryMovie movie = diaryMovieDAO.findByDiaryIdDiaryId(diaryId);
        DiaryMusic music = diaryMusicDAO.findByDiaryIdDiaryId(diaryId);
        System.out.println("diary = " + diary);
        System.out.println("food = " + food.getFoodId());
        System.out.println("emotion = " + emotion.getEmotionId());
        System.out.println("music = " + music.getMusicId());
        System.out.println("movie = " + movie.getMovieId());
//        diaryMovieDAO.findByDiaryId(diaryId);
//        diaryMusicDAO.findByDiaryId(diaryId);


        return null;
    }


}
