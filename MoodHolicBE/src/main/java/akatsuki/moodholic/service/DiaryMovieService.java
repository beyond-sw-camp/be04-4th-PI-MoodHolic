package akatsuki.moodholic.service;

import akatsuki.moodholic.domain.Diary;
import akatsuki.moodholic.domain.DiaryMovie;

import java.util.ArrayList;
import java.util.List;

public interface DiaryMovieService {
    DiaryMovie findMovieByDiaryId(int diaryId);

    void saveDiaryMovie(DiaryMovie diaryMovie);

    void delete(int diaryId);

    List<DiaryMovie> findLikedDiaryMovies();
    List<String> findLikedMovieNames();

    public List<DiaryMovie> getMemberLikedMovie(List<Diary> diaries );

    void likeMovie(int diaryId, boolean movie);
}
