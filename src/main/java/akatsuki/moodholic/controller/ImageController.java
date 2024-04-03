package akatsuki.moodholic.controller;

import akatsuki.moodholic.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/image")
public class ImageController {
    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("")
    public ResponseEntity<String> get(@RequestParam("file") MultipartFile file) throws IOException {
        System.out.println("profile upload request..");
        String imageUrl = imageService.uploadImage(file);
        System.out.println("imageUrl = " + imageUrl);
        return ResponseEntity.status(HttpStatus.OK).body(imageUrl);
    }
}
