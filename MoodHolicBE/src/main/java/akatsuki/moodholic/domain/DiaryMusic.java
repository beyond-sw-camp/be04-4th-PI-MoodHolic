package akatsuki.moodholic.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "diary_music")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class DiaryMusic {
    @Id
    @Column(name = "diary_music_id")
    private int diaryMusicId;
    @Column(name = "diary_id")
    private int diaryId;
    @Column(name = "music_id")
    private int MusicId;
}
