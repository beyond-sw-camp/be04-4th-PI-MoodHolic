package akatsuki.moodholic.diary.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class DiaryDTO {

    private int diaryId;
    private String content;
    private boolean status;
    private String imgPath;
}