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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int diaryMovieId;
    @JoinColumn(name = "diary_id")
    @OneToOne
    private Diary diaryId;
    @JoinColumn(name = "movie_id")
    @ManyToOne
    private Movie movieId;

    @Column(name = "movie_like")
    private int movieLike;

    public DiaryMovie(Diary diary, Movie movie, int movieLike) {
        this.diaryId = diary;
        this.movieId = movie;
        this.movieLike = movieLike;
    }
}
