package akatsuki.moodholic.service;

import akatsuki.moodholic.domain.Diary;
import akatsuki.moodholic.dto.ResponseDiary;

import java.util.List;

public interface DiaryService {

    String postDiary(Diary diary);

    String deleteDiary(int diaryId);

    List<Diary> getMemberDiaries(long memberid);

    Diary findDiary(int diaryId);

    List<Diary> findAllByMemberOrderByDateAsc(long memberId);

    Long getMemberDiaryCnt(long memberId);

    Diary getDiaryByDiaryId(int diaryId);
}
