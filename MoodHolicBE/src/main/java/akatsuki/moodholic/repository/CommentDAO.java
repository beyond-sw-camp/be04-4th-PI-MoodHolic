package akatsuki.moodholic.repository;

import akatsuki.moodholic.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentDAO extends JpaRepository<Comment,Integer> {

    Comment findByDiaryId(int diaryId);

    void deleteByDiaryId(int diaryId);
}
