package akatsuki.moodholic.emotion.service;

import akatsuki.moodholic.emotion.dao.EmotionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmotionServiceImpl implements EmotionService{
    EmotionDAO emotionDAO;

    @Autowired
    public EmotionServiceImpl(EmotionDAO emotionDAO) {
        this.emotionDAO = emotionDAO;
    }
}
