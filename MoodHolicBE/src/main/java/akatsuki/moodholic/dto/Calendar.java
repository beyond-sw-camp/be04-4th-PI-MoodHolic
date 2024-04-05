package akatsuki.moodholic.dto;

import lombok.Data;

@Data
public class Calendar {
    private int status;
    private String date;

    public Calendar(int status, String date) {
        this.status=status;
        this.date=date;
    }
}
