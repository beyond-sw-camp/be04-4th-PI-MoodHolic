package akatsuki.moodholic.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="emotion")
@Data
public class Emotion {

    @Id
    @Column(name="emotion_id")
    private int emotionId;

    @Column(name = "emotion-score")
    private int emotionScore;
}
