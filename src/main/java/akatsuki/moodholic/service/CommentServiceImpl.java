package akatsuki.moodholic.service;

import akatsuki.moodholic.domain.Comment;
import akatsuki.moodholic.domain.DiaryEmotion;
import akatsuki.moodholic.repository.CommentDAO;
import akatsuki.moodholic.repository.DiaryEmotionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService{

    CommentDAO commentDAO;

    @Autowired
    public CommentServiceImpl(CommentDAO commentDAO) {
        this.commentDAO = commentDAO;
    }
    @Override
    public  Comment findCommentByDiaryId(int diaryId){
        return commentDAO.findByDiaryId(diaryId);
    }

    @Override
    public  void saveComment(Comment comment){
        commentDAO.save(comment);
    }
    @Override
    public void delete(int diaryId){
        commentDAO.deleteByDiaryId(diaryId);
    }

}
