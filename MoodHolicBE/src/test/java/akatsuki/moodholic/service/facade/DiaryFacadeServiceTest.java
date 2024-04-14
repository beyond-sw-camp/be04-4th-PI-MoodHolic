package akatsuki.moodholic.service.facade;

import akatsuki.moodholic.domain.Diary;
import akatsuki.moodholic.dto.ResponseDiary;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class DiaryFacadeServiceTest {
    @Autowired
    DiaryFacadeService diaryFacadeService;

    int diaryId;
    /*given*/
    /*when*/
    /*then*/


    @Test
    @DisplayName("다이어리 정보 불러오기")
    void getDiary(){
        /*given*/
        diaryId=10;
        /*when*/
        ResponseDiary responseDiary = diaryFacadeService.getDiary(diaryId);
        /*then*/
        assertEquals(diaryId,responseDiary.getDiary().getDiaryId());
    }
    @Test
    @DisplayName("삭제되거나 존재하지 않는 다이어리 정보 불러오기")
    void getNullDiary(){
        /*given*/
        diaryId=1;
        /*then*/
        assertThrows(IllegalArgumentException.class, ()->{
            /*when*/
            diaryFacadeService.getDiary(diaryId);
        });
    }

//    다이어리 임시저장
    @Test
    @DisplayName("다이어리 임시저장")
    void postDiary1(){

        /*given*/
        Diary diary = new Diary();
        /*when*/

        /*then*/

    }
//    다이어리 저장
    @Test
    @DisplayName("다이어리 저장")
    void postDiary2(){
        /*given*/

        /*when*/

        /*then*/

    }
//    다이어리 중복저장
    @Test
    @DisplayName("다이어리 중복저장")
    void postDiary3(){
        /*given*/

        /*when*/

        /*then*/
    }

    @Test
    @DisplayName("다이어리 제거")
    void deleteDiary(){
        /*given*/
        diaryId=1;
        /*when*/
        String response = diaryFacadeService.deleteDiary(diaryId);
        /*then*/
        assertEquals("삭제 완료",response);
    }

    @Test
    @DisplayName("추천 항목 좋아요 추가")
    void putMemberLike(){
        diaryId=10;
        boolean food=true;
        boolean music=true;
        boolean movie=true;
        String response = diaryFacadeService.putMemberLike(diaryId,food,music,movie);

        assertEquals("좋아요 입력 완료",response);
//        assertEquals(food);
    }
}