package akatsuki.moodholic.service;

import akatsuki.moodholic.domain.DiaryMovie;
import akatsuki.moodholic.repository.DiaryMovieDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiaryMovieServiceImpl implements DiaryMovieService{

    DiaryMovieDAO diaryMovieDAO;

    @Autowired
    public DiaryMovieServiceImpl(DiaryMovieDAO diaryMovieDAO) {
        this.diaryMovieDAO = diaryMovieDAO;
    }

    @Override
    public DiaryMovie findMovieByDiaryId(int diaryId){
        return diaryMovieDAO.findByDiaryId(diaryId);
    }

    @Override
    public void saveDiaryMovie(DiaryMovie diaryMovie){
        diaryMovieDAO.save(diaryMovie);
    }

    @Override
    public void delete(int diaryId){
        diaryMovieDAO.deleteByDiaryId(diaryId);
    }
}
