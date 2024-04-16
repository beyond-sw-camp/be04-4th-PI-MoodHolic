package akatsuki.moodholic.service.facade;

import akatsuki.moodholic.domain.Diary;
import akatsuki.moodholic.domain.DiaryFood;
import akatsuki.moodholic.domain.Food;
import akatsuki.moodholic.dto.MemberFoodGenreRanking;
import akatsuki.moodholic.repository.DiaryFoodDAO;
import akatsuki.moodholic.repository.FoodDAO;
import akatsuki.moodholic.service.DiaryFoodService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FoodFacadeServiceTest {
    @Autowired
    FoodFacadeService foodFacadeService;

    @Autowired
    FoodDAO foodDAO;
    @Autowired
    DiaryFoodDAO diaryFoodDAO;
    @Autowired
    DiaryFacadeService diaryFacadeService;
    @Autowired
    DiaryFoodService diaryFoodService;

    /*given*/
    /*when*/
    /*then*/

    @Test
    @DisplayName("모든 음식 조회")
    public void getAllFood(){
        List<Food> foodList1 = foodFacadeService.getAllFoods();
        List<Food> foodList2 = foodDAO.findAll();
        assertEquals(foodList1,foodList2);
    }

    @Test
    @DisplayName("좋아요 된 모든 음식 조회")
    public void findLikedDiaryFoods() {
        long memberId=1;
        List<DiaryFood> diaryFoodList1 = foodFacadeService.findLikedDiaryFoods();
        List<DiaryFood> diaryFoodList2 = diaryFoodDAO.findByFoodLikeTrue();

        assertEquals(diaryFoodList1.size(),diaryFoodList2.size());
    }

    @Test
    @DisplayName("좋아요 표시된 음식 이름 횟수")
    public void countFoodNameWithLikes(){
        long memberId =1;
        HashMap<String,Integer> foodNames = foodFacadeService.countFoodNameWithMemberLike(memberId);
        List<DiaryFood> diaryFoodList = foodFacadeService.getMemberLikeFood(memberId);
        diaryFoodList.forEach(diaryFood -> {
            String name =diaryFood.getFoodId().getFoodName();
            foodNames.replace(name, foodNames.get(name)-1);
        });
        foodNames.forEach((strKey, strValue)->{
            assertEquals(0,strValue);
        });
    }

    @Test
    @DisplayName("맴버가 음식 좋아요 체크한 리스트")
    public void getMemberLikeFood(){
        long memberId=1;
        List<DiaryFood> diaryFoodList = foodFacadeService.getMemberLikeFood(memberId);
        diaryFoodList.forEach(diaryFood->{
            assertEquals(true,diaryFood.getFoodLike());
        });
    }

    @Test
    @DisplayName("멤버가 좋아요 한 음식 카테고리 랭킹")
    public void getMemberFoodGenreRanking(){
        long memberId= 1;
        MemberFoodGenreRanking memberFoodGenreRanking = foodFacadeService.getMemberFoodGenreRanking(memberId);
        List<Diary> diaryList = diaryFacadeService.getMemberDiaries(memberId);
        List<DiaryFood> diaryFoodList = diaryFoodService.getMemberLikeFood(diaryList);

        diaryFoodList.forEach(diaryFood->{
            if (diaryFood.getFoodId().getFoodCategory().name().equals(memberFoodGenreRanking.getTopName())){
                memberFoodGenreRanking.setTopCnt(memberFoodGenreRanking.getTopCnt()-1);
            }
        });
        // 가장 많이 좋아요 받은 데이터 카운트를 - 시켜 확인
        assertEquals(0,memberFoodGenreRanking.getTopCnt());
    }

}