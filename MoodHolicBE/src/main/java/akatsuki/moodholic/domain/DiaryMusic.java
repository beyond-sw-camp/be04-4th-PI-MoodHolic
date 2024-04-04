package akatsuki.moodholic.domain;

import jakarta.persistence.*;
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
    @JoinColumn(name = "diary_id")
    @OneToOne
    private Diary diaryId;
    @JoinColumn(name = "music_id")
    @OneToOne
    private Music MusicId;
}
