package akatsuki.moodholic.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "diary_movie")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class DiaryMovie {
    @Id
    @Column(name = "diary_movie_id")
    private int diaryMovieId;
    @Column(name = "diary_id")
    private int diaryId;
    @Column(name = "movie_id")
    private int MovieId;
}
