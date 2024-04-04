package akatsuki.moodholic.controller;

import akatsuki.moodholic.domain.DiaryFood;
import akatsuki.moodholic.domain.Food;
import akatsuki.moodholic.repository.FoodDAO;
import akatsuki.moodholic.service.FoodService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category/food")
@Tag(name = "음식 조회 컨트롤러", description = "추천받은 음식을 조회하는 기능")
public class FoodController {

    private  final FoodService foodService;

    @Autowired
    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    // 모든 음식 목록 조회
    @GetMapping("/all")
    @Operation(summary = "음식 전체 조회", description = "단순 음식 조회 기능")
    public List<Food> getAllFoods() {
        return foodService.getAllFoods();
    }

    @GetMapping("/liked")
    @Operation(summary = "좋아요 표시된 음식 조회", description = "사용자가 좋아요 표시한 음식 조회 기능")
    public List<DiaryFood> getLikedDiaryFoods() {
        return foodService.findLikedDiaryFoods();
    }

    @GetMapping("/liked/names")
    public List<String> getLikedFoodNames() {
        return foodService.findLikedFoodNames();
    }


}
