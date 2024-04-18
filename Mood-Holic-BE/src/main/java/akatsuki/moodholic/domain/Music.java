package akatsuki.moodholic.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "music")
@Data
public class Music {
    @Id
    @Column(name = "music_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int musicId;
    @Column(name = "music_name")
    private String musicName;
    @Column(name = "singer")
    private String singer;
    @Column(name = "music_genre")
    private String musicGenre;
}
