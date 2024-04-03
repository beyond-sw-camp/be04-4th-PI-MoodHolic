package akatsuki.moodholic.music.service;

import akatsuki.moodholic.music.dao.MusicDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MusicServiceImpl implements MusicService{
    private MusicDAO musicDAO;

    @Autowired
    public MusicServiceImpl(MusicDAO musicDAO) {
        this.musicDAO = musicDAO;
    }
}
