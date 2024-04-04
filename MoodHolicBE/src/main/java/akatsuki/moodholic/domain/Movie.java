package akatsuki.moodholic.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "movie")
@Data
public class Movie {
    @Id
    @Column(name = "movie_id")
    private int movieId;
    @Column(name = "movie_name")
    private String movieName;
    @Column(name = "movie_genre")
    private String movieGenre;
}
