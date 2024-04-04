package akatsuki.moodholic.domain;

import jakarta.persistence.*;
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
    @JoinColumn(name = "diary_id")
    @OneToOne
    private Diary diaryId;
    @JoinColumn(name = "movie_id")
    @OneToOne
    private Movie MovieId;
}
