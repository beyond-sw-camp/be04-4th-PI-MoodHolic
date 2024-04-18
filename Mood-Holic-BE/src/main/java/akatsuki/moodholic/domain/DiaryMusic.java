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

    @Column(name = "diary_id")
    private int diaryId;

    @JoinColumn(name = "music_id")
    @ManyToOne
    private Music musicId;

    @Column(name = "music_like")
    private boolean musicLike;

    public DiaryMusic(Diary diary, Music music, boolean musicLike) {
        this.diaryId = diary.getDiaryId();
        this.musicId = music;
        this.musicLike = musicLike;
    }

}
