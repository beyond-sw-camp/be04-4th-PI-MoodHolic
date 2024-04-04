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

    private DiaryMovieDAO diaryMovie;
    private DiaryFoodDAO diaryFood;
    private DiaryMusicDAO diaryMusic;
    private DiaryEmotionDAO diaryEmotion;
    private final DiaryEmotionDAO diaryEmotionDAO;
    private final DiaryFoodDAO diaryFoodDAO;
    private final DiaryMovieDAO diaryMovieDAO;

    @Autowired
    public DiaryService(MemberDAO memberDAO, DiaryDAO diaryDAO, EmotionDAO emotionDAO, FoodDAO foodDAO, MovieDAO movieDAO, MusicDAO musicDAO, DiaryMovieDAO diaryMovie, DiaryFoodDAO diaryFood, DiaryMusicDAO diaryMusic, DiaryEmotionDAO diaryEmotion,
                        DiaryEmotionDAO diaryEmotionDAO,
                        DiaryFoodDAO diaryFoodDAO,
                        DiaryMovieDAO diaryMovieDAO) {
        this.memberDAO = memberDAO;
        this.diaryDAO = diaryDAO;
        this.emotionDAO = emotionDAO;
        this.foodDAO = foodDAO;
        this.movieDAO = movieDAO;
        this.musicDAO = musicDAO;
        this.diaryEmotion = diaryEmotion;
        this.diaryMovie = diaryMovie;
        this.diaryFood = diaryFood;
        this.diaryMusic = diaryMusic;
        this.diaryEmotionDAO = diaryEmotionDAO;
        this.diaryFoodDAO = diaryFoodDAO;
        this.diaryMovieDAO = diaryMovieDAO;
    }

    public ResponseDiary getDiary(int diaryId) {
        Diary diary = diaryDAO.findById(diaryId).orElseThrow();

        return null;
    }


}
