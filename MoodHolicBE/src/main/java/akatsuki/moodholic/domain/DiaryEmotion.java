package akatsuki.moodholic.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashMap;
import java.util.List;

@Entity
@Table(name = "diary_emotion")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class DiaryEmotion {
    @Id
    @Column(name = "diary_emotion_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int diaryEmotionId;

    @JoinColumn(name = "diary_id")
    @OneToOne
    private Diary diaryId;

    @Column(name = "emotion_id")
    private int EmotionId;


    public DiaryEmotion(Diary diary, int emotionScore) {
        this.diaryId=diary;
        this.EmotionId = emotionScore;
    }


}
