package akatsuki.moodholic.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDiaryPost {
    private String response;
    private int dirayId;

    public ResponseDiaryPost(String prompt, int diaryId) {
        this.dirayId =diaryId;
        this.response = prompt;
    }

    public ResponseDiaryPost(String prompt) {
        this.response=prompt;
    }
}
