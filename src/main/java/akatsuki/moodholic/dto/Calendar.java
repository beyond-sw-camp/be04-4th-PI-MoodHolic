package akatsuki.moodholic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Calendar {
    private int diaryId;
    private int status;
    private String emotion;
    private String year;
    private String month;
    private String day;
    private String summary;
    private String content;

}
