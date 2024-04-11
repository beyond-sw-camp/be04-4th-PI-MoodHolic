package akatsuki.moodholic.dto;

import lombok.Data;

@Data
public class Calendar {
    private int status;
    private String year;
    private String month;
    private String day;


    public Calendar(int status,String year, String month,String day) {
        this.status=status;
        this.year=year;
        this.month=month;
        this.day=day;
    }
}
