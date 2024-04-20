package akatsuki.moodholic.domain;

import jakarta.persistence.*;
import lombok.*;

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
    private Diary diary;

    @Column(name = "emotion_id")
    private int EmotionId;


    public DiaryEmotion(Diary diary, int emotionScore) {
        this.diary =diary;
        this.EmotionId = emotionScore;
    }

    public int getDiaryId(){
        return this.diary.getDiaryId();
    }


}
