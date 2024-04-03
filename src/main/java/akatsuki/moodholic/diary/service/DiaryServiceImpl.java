package akatsuki.moodholic.diary.service;

import akatsuki.moodholic.emotion.dao.EmotionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiaryServiceImpl implements DiaryService{

    EmotionDAO emotionDAO;

    @Autowired
    public DiaryServiceImpl(EmotionDAO emotionDAO) {
        this.emotionDAO = emotionDAO;
    }
}
