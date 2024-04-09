package akatsuki.moodholic.service;

import akatsuki.moodholic.domain.DiaryFood;
import akatsuki.moodholic.repository.DiaryFoodDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiaryFoodServiceImpl implements DiaryFoodService{
    DiaryFoodDAO diaryFoodDAO;

    @Autowired
    public DiaryFoodServiceImpl(DiaryFoodDAO diaryFoodDAO) {
        this.diaryFoodDAO = diaryFoodDAO;
    }

    @Override
    public DiaryFood findFoodByDiaryId(int diaryId){
        return diaryFoodDAO.findByDiaryId(diaryId);
    }

    @Override
    public void saveDiaryFood(DiaryFood diaryFood){
        diaryFoodDAO.save(diaryFood);
    }

    @Override
    public void delete(int diaryId){
        diaryFoodDAO.deleteByDiaryId(diaryId);
    }
}
