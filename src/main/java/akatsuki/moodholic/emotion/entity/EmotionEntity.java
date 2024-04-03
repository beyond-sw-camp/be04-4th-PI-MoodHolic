package akatsuki.moodholic.emotion.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="emotion")
@Data
public class EmotionEntity {

    @Id
    @Column(name="emotion_id")
    private int emotionId;

    @Column(name = "emotion-score")
    private int emotionScore;
}
