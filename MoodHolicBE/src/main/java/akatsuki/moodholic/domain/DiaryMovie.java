package akatsuki.moodholic.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int diaryMovieId;

    @Column(name = "diary_id")
    private int diaryId;

    @JoinColumn(name = "movie_id")
    @ManyToOne
    private Movie movieId;
    
    @Column(name = "movie_like")
    private boolean movieLike;

    public DiaryMovie(Diary diary, Movie movie, boolean movieLike) {
        this.diaryId = diary.getDiaryId();
        this.movieId = movie;
        this.movieLike = movieLike;
    }
}
