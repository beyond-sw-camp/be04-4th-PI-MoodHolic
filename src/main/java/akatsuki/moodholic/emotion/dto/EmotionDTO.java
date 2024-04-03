package akatsuki.moodholic.emotion.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class EmotionDTO {
    private int emotionId;
    private int emotionScore;
}
