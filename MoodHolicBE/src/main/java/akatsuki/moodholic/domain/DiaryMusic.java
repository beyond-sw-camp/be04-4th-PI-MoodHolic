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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int diaryMusicId;
    @JoinColumn(name = "diary_id")
    @OneToOne
    private Diary diaryId;
    @JoinColumn(name = "music_id")
    @ManyToOne
    private Music musicId;
    @Column(name = "music_like")
    private int musicLike;

    public DiaryMusic(Diary diary, Music music, int musicLike) {
        this.diaryId = diary;
        this.musicId = music;
        this.musicLike = musicLike;
    }
}
