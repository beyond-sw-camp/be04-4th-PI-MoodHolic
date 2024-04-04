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
    private int diaryEmotionId;

    @JoinColumn(name = "diary_id")
    @OneToOne
    private Diary diaryId;
    @JoinColumn(name = "emotion_id")
    @OneToOne
    private Emotion EmotionId;
}
