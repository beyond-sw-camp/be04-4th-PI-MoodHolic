package akatsuki.moodholic.service;

import akatsuki.moodholic.domain.DiaryMovie;

public interface DiaryMovieService {
    DiaryMovie findMovieByDiaryId(int diaryId);

    void saveDiaryMovie(DiaryMovie diaryMovie);

    void delete(int diaryId);

}
