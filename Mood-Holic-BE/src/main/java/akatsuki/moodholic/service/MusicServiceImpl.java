package akatsuki.moodholic.service;

import akatsuki.moodholic.domain.Music;
import akatsuki.moodholic.repository.DiaryMusicDAO;
import akatsuki.moodholic.repository.MusicDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicServiceImpl implements MusicService{

    private final MusicDAO musicDAO;

    @Autowired
    public MusicServiceImpl(MusicDAO musicDAO) {
        this.musicDAO = musicDAO;
    }

    @Override
    public List<Music> getAllMusics() {
        return musicDAO.findAll();
    }

    @Override
    public List<Object[]> countMusicGenresWithLikes() {
        return musicDAO.countMusicGenresWithLikes();
    }

    @Override
    public Music findByMusicName(String musicName){
        return musicDAO.findByMusicName(musicName);
    }

    @Override
    public Music saveMusic(Music music){
        return musicDAO.save(music);
    }

}
