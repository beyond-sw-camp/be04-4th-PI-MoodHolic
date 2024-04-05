package akatsuki.moodholic.domain;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="emotion")
@Data
public class Emotion {

    @Id
    @Column(name="emotion_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int emotionId;

    @Column(name = "emotion-score")
    private int emotionScore;
}
