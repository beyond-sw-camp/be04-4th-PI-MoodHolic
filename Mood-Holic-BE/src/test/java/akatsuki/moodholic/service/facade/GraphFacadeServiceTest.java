package akatsuki.moodholic.service.facade;

import akatsuki.moodholic.domain.Diary;
import akatsuki.moodholic.repository.DiaryDAO;
import akatsuki.moodholic.repository.DiaryEmotionDAO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GraphFacadeServiceTest {

    @Autowired
    GraphFacadeService graphFacadeService;
    @Autowired
    DiaryDAO diaryDAO;
    @Autowired
    DiaryEmotionDAO diaryEmotionDAO;

    long memberId=1;

    double sum=0;
    int cnt=0;

    @Test
    @DisplayName("년도별 감정 점수 조회")
    void getEmotionYear(){
        Map<String, Double> data = graphFacadeService.GetEmotionYear(memberId);
        List<Diary> diaryList = diaryDAO.findAllByMemberMemberId(memberId);
        data.forEach((date,avg)->{
            sum=0;
            cnt=0;
            diaryList.forEach(diary->{
                String[] diaryDate = diary.getDate().split("-");
//                System.out.println("date = " + date);
//                System.out.println("diaryDate[0] = " + diaryDate[0]);
                if(date.equals(diaryDate[0]) && diaryEmotionDAO.findByDiaryDiaryId(diary.getDiaryId())!= null){
                    cnt++;
                    sum+=diaryEmotionDAO.findByDiaryDiaryId(diary.getDiaryId()).getEmotionId();
                }
            });
            double result=Math.round((sum/cnt)*100)/100.0d;
            assertEquals(avg,result);
        });

    }


    @Test
    @DisplayName("월별 감정 점수 조회")
    void getEmotionMonth(){
        Map<String, Double> data = graphFacadeService.GetEmotionMonth(memberId);
        List<Diary> diaryList = diaryDAO.findAllByMemberMemberId(memberId);
        data.forEach((date,avg)->{
            sum=0;
            cnt=0;
            diaryList.forEach(diary->{
                String[] diaryDate = diary.getDate().split("-");
                if(date.equals(diaryDate[0]+"-"+diaryDate[1]) && diaryEmotionDAO.findByDiaryDiaryId(diary.getDiaryId())!= null){
                    cnt++;
                    sum+=diaryEmotionDAO.findByDiaryDiaryId(diary.getDiaryId()).getEmotionId();
                }
            });
            double result=Math.round((sum/cnt)*100)/100.0d;
            assertEquals(avg,result);
        });

    }


    @Test
    @DisplayName("주별 감정 점수 조회")
    void getEmotionWeek(){
        Map<String, Double> data = graphFacadeService.GetEmotionWeek(memberId);
        List<Diary> diaryList = diaryDAO.findAllByMemberMemberId(memberId);
        data.forEach((date,avg)->{
            sum=0;
            cnt=0;
            diaryList.forEach(diary->{
                String[] diaryDate = diary.getDate().split("-");
                int dayOfMonth = Integer.parseInt(diaryDate[2]);
                int weekOfMonth = (dayOfMonth - 1) / 7 + 1;
                String cmpDate = diaryDate[0] + "-" + diaryDate[1] + "-W" + weekOfMonth;
                if(date.equals(cmpDate) && diaryEmotionDAO.findByDiaryDiaryId(diary.getDiaryId())!= null){
                    cnt++;
                    sum+=diaryEmotionDAO.findByDiaryDiaryId(diary.getDiaryId()).getEmotionId();
                }
            });

            double result=Math.round((sum/cnt)*100)/100.0d;
            assertEquals(avg,result);
        });
    }

}