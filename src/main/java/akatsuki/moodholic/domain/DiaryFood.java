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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int diaryFoodId;

    @Column(name = "diary_id")
    private int diaryId;

    @JoinColumn(name = "food_id")
    @ManyToOne
    private Food foodId;

    @Column(name = "food_like")
    private Boolean foodLike;
    
    public DiaryFood(Diary diaryId, Food foodId, boolean foodLike) {
        this.diaryId = diaryId.getDiaryId();
        this.foodLike = foodLike;
        this.foodId = foodId;
    }
}
