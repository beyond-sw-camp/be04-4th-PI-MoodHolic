package akatsuki.moodholic.service;

import akatsuki.moodholic.domain.Diary;
import akatsuki.moodholic.domain.DiaryMovie;
import akatsuki.moodholic.repository.DiaryMovieDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class DiaryMovieServiceImpl implements DiaryMovieService{

    DiaryMovieDAO diaryMovieDAO;

    @Autowired
    public DiaryMovieServiceImpl(DiaryMovieDAO diaryMovieDAO) {
        this.diaryMovieDAO = diaryMovieDAO;
    }

    @Override
    public DiaryMovie findMovieByDiaryId(int diaryId){
        return diaryMovieDAO.findByDiaryIdDiaryId(diaryId);
    }

    @Override
    public void saveDiaryMovie(DiaryMovie diaryMovie){
        diaryMovieDAO.save(diaryMovie);
    }

    @Override
    public void delete(int diaryId){
        diaryMovieDAO.deleteByDiaryIdDiaryId(diaryId);
    }

    @Override
    public List<DiaryMovie> findLikedDiaryMovies() {
        return diaryMovieDAO.findByMovieLoveTrue();
    }
    @Override
    public List<String> findLikedMovieNames() {
        return diaryMovieDAO.findLikedMovieNames();
    }

    @Override
    public List<DiaryMovie> getMemberLikedMovie(long memberId){
        return  diaryMovieDAO.findAllByDiaryIdMemberMemberIdAndMovieLoveTrue(memberId);
    }

    // 개선 전
    @Override
    public List<DiaryMovie> getMemberLikedMovie2(List<Diary> diaries ){
        List<DiaryMovie> returnValue= new ArrayList<>();
        diaries.forEach(diary -> {
            DiaryMovie diaryMovie = diaryMovieDAO.findByDiaryIdDiaryId(diary.getDiaryId());
            System.out.println("diaryMovie = " + diaryMovie);
            if(diaryMovie!=null)
                returnValue.add(diaryMovie);
        });
        return returnValue;
    }

    @Override
    @Transactional
    public void likeMovie(int diaryId, boolean movie){
        DiaryMovie diaryMovie = diaryMovieDAO.findByDiaryIdDiaryId(diaryId);
        diaryMovie.setMovieLove(movie);

    }
}
