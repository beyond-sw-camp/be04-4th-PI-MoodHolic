package akatsuki.moodholic.dto;


import lombok.Data;

@Data
public class ResponseImage {
    private String imgUrl;

    public ResponseImage(String imageUrl) {
        this.imgUrl=imageUrl;
    }
}
