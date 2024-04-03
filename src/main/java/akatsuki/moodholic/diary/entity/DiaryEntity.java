package akatsuki.moodholic.diary.entity;

import akatsuki.moodholic.member.dto.MemberDTO;
import akatsuki.moodholic.member.entity.MemberEntity;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name="diary")
@Data
public class DiaryEntity {

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
    private MemberEntity member;


}
