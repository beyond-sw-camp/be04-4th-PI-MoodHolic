package akatsuki.moodholic.domain;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name="diary")
@Data
public class Diary {

    @Id
    @Column(name = "diary_id")
    private int diaryId;

    @Column(name = "date")
    private String date;
    @Column(name = "content")
    private String content;

    @Column(name = "status")
    private boolean status;

    @Column(name = "img_path")
    private String imgPath;

    @ManyToOne
    @JoinColumn(name= "member_id")
    private Member member;


}
