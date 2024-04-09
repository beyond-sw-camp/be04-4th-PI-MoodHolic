package akatsuki.moodholic.service;

import akatsuki.moodholic.domain.Diary;
import akatsuki.moodholic.dto.ResponseDiary;

import java.util.List;

public interface DiaryService {

    ResponseDiary getDiary(int diaryId);

    String postDiary(Diary diary);

    String deleteDiary(int diaryId);

    List<Diary> getMemberDiaries(long memberid);

    Diary findDiary(int diaryId);
}
