package akatsuki.moodholic.service;

import akatsuki.moodholic.domain.Music;
import akatsuki.moodholic.repository.MusicDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicServiceImpl implements MusicService{

    private final MusicDAO musicrepository;

    @Autowired
    public MusicServiceImpl(MusicDAO musicrepository) {
        this.musicrepository = musicrepository;
    }

    @Override
    public List<Music> getAllMusics() {
        return musicrepository.findAll();
    }
}
