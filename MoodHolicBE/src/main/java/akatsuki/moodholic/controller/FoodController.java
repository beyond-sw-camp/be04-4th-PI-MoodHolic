package akatsuki.moodholic.controller;

import akatsuki.moodholic.domain.DiaryFood;
import akatsuki.moodholic.domain.Food;
import akatsuki.moodholic.service.facade.FoodFacadeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/category/food")
@Tag(name = "음식 조회 컨트롤러", description = "추천받은 음식을 조회하는 기능")
public class FoodController {


    private final FoodFacadeService foodFacadeService;

    @Autowired
    public FoodController(FoodFacadeService foodFacadeService) {
        this.foodFacadeService = foodFacadeService;
    }

    // 모든 음식 목록 조회
    @GetMapping("/all")
    @Operation(summary = "음식 전체 조회", description = "단순 음식 조회 기능")
    public ResponseEntity<List<Food>> getAllFoods() {
        return ResponseEntity.ok().body(foodFacadeService.getAllFoods());
    }

    @GetMapping("/liked")
    @Operation(summary = "좋아요 표시된 음식 조회", description = "모든 사용자가 좋아요 표시한 음식 조회 기능")
    public ResponseEntity<List<DiaryFood>> getLikedDiaryFoods() {
        return ResponseEntity.ok().body(foodFacadeService.findLikedDiaryFoods());
    }

    @GetMapping("/liked/names")
    public ResponseEntity<List<String>> getLikedFoodNames() {
        return ResponseEntity.ok().body(foodFacadeService.findLikedFoodNames());
    }

    @GetMapping("/category/likes-count")
    public ResponseEntity<List<Object[]>> countFoodCategorysWithLikes() {
        return ResponseEntity.ok().body(foodFacadeService.countFoodCategorysWithLikes());
    }

    @GetMapping("/liked/{memberId}")
    @Operation(summary = "회원의 좋아요 표시된 음식 조회", description = "한 회원이 좋아요 표시한 음식 조회 기능")
    public ResponseEntity<List<DiaryFood>> getMemberLikeFood(@PathVariable long memberId){
        return ResponseEntity.ok().body(foodFacadeService.getMemberLikeFood(memberId));
    }

    @GetMapping("/liked/{memberId}/count")
    @Operation(summary = "회원의 좋아요 표시된 음식 횟수 조회", description = "한 회원이 좋아요 표시한 음식 횟수 조회 기능")
    public ResponseEntity<HashMap<String,Integer>> countFoodCategoryWithMemberLike(@PathVariable long memberId){
        return ResponseEntity.ok().body( foodFacadeService.countFoodCategoryWithMemberLike(memberId) ) ;
    }


}
