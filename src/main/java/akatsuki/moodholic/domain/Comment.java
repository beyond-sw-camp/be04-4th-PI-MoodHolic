package akatsuki.moodholic.domain;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="comment")
@Data
public class Comment {
    @Id
    @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commentId;
    @Column(name = "diary_id")
    private long diaryId;
    @Column(name = "comment_content")
    private String commentContent;

    public Comment() {}

    public Comment(Diary diary, String commentContent) {
        this.diaryId = diary.getDiaryId();
        this.commentContent = commentContent;
    }
}
