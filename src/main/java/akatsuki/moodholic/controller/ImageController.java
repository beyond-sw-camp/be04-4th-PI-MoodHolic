package akatsuki.moodholic.controller;

import akatsuki.moodholic.service.ImageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/image")
@Tag(name = "이미지 업로드 컨트롤러", description = "회원 프로필 사진 또는 다이어리 사진을 업로드 하기 위한 기능")
public class ImageController {
    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }


    @PostMapping("")
    @Operation(summary = "이미지 업로드", description = "단순 이미지 업로드 기능")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) throws IOException {
        System.out.println("image upload request..");
        String imageUrl = imageService.uploadImage(file);
        System.out.println("imageUrl = " + imageUrl);
        return ResponseEntity.status(HttpStatus.OK).body(imageUrl);
    }

    @PostMapping("/profile/{memberId}")
    @Operation(summary = "프로필 이미지 업로드", description = "프로필 이미지 업로드 기능")
    public ResponseEntity<String> uploadProfile(@PathVariable long memberId ,@RequestParam("file") MultipartFile file) throws IOException {
        System.out.println("profile image upload request..");
        String imageUrl = imageService.profileUploadImage(memberId,file);
        System.out.println("imageUrl = " + imageUrl);
        return ResponseEntity.status(HttpStatus.OK).body(imageUrl);
    }

    @PostMapping("/diary/{diaryId}")
    @Operation(summary = "다이어리 이미지 업로드", description = "다이어리 이미지 업로드 기능")
    public ResponseEntity<String> uploadDiray(@PathVariable int diaryId ,@RequestParam("file") MultipartFile file) throws IOException {
        System.out.println("diary image upload request..");
        String imageUrl = imageService.diaryUploadImage(diaryId,file);
        System.out.println("imageUrl = " + imageUrl);
        return ResponseEntity.status(HttpStatus.OK).body(imageUrl);
    }
}
