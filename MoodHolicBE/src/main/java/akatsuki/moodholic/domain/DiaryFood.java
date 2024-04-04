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
    @JoinColumn(name = "diary_id")
    @OneToOne
    private Diary diaryId;
    @JoinColumn(name = "food_id")
    @OneToOne
    private Food FoodId;
}
