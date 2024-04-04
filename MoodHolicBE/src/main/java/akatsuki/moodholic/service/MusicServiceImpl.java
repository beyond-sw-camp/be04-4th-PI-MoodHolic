package akatsuki.moodholic.service;

import akatsuki.moodholic.domain.DiaryMusic;
import akatsuki.moodholic.domain.Music;
import akatsuki.moodholic.repository.DiaryMusicDAO;
import akatsuki.moodholic.repository.MusicDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicServiceImpl implements MusicService{

    private final MusicDAO musicrepository;
    private final DiaryMusicDAO diarymusicrepository;

    @Autowired
    public MusicServiceImpl(MusicDAO musicrepository, DiaryMusicDAO diarymusicrepository) {
        this.musicrepository = musicrepository;
        this.diarymusicrepository = diarymusicrepository;
    }

    @Override
    public List<Music> getAllMusics() {
        return musicrepository.findAll();
    }

    @Override
    public List<DiaryMusic> findLikedDiaryMusics() {
        return diarymusicrepository.findByMusicLikeTrue();
    }

    @Override
    public List<String> findLikedMusicNames() {
        return diarymusicrepository.findLikedMusicNames();
    }
}
