package akatsuki.moodholic.service;

import akatsuki.moodholic.domain.Comment;

public interface CommentService {
    Comment findCommentByDiaryId(int diaryId);

    void saveComment(Comment comment);

    void delete(int diaryId);

}
