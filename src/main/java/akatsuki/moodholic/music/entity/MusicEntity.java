package akatsuki.moodholic.music.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "music")
@Data
public class MusicEntity {
    @Id
    @Column(name = "music_id")
    private int musicId;
    @Column(name = "music_name")
    private String musicName;
    @Column(name = "singer")
    private String singer;
    @Column(name = "music_genre")
    private String musicGenre;
}
