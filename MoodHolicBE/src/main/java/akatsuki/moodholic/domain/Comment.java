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
    @JoinColumn(name = "diary_id")
    @ManyToOne
    private Diary diaryId;
    @Column(name = "comment_content")
    private String commentContent;

    public Comment() {}

    public Comment(Diary diary, String commentContent) {
        this.diaryId = diary;
        this.commentContent = commentContent;
    }
}
