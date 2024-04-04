package akatsuki.moodholic.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "diary_food")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class DiaryFood {
    @Id
    @Column(name = "diary_food_id")
    private int diaryFoodId;
    @Column(name = "diary_id")
    private int diaryId;
    @Column(name = "food_id")
    private int foodId;
    @Column(name = "food_like")
    private Boolean foodLike;
}
