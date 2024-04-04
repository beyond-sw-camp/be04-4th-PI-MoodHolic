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
    @Column(name = "diary_id")
    private int diaryId;
    @Column(name = "emotion_id")
    private int EmotionId;
}
