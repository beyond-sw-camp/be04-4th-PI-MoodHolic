package akatsuki.moodholic.dto;

import java.util.Map;

public class NaverResponse implements OAuth2Response {

    private final Map<String, Object> attribute;

    public NaverResponse(Map<String, Object> attribute) {

        this.attribute = (Map<String, Object>) attribute.get("response");
    }

    @Override
    public String getProvider() {

        return "naver";
    }

    @Override
    public String getProviderId() {

        return attribute.get("id").toString();
    }

    @Override
    public String getEmail() {

        return attribute.get("email").toString();
    }

    @Override
    public String getName() {
        Object name = attribute.get("name");
        return name != null ? name.toString() : null;
    }

    @Override
    public String getThumbnail() {
        Object thumbnail = attribute.get("profile_image");
        return thumbnail != null ? thumbnail.toString() : null;
    }
}
