package akatsuki.moodholic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.filter.CharacterEncodingFilter;

@SpringBootApplication
@EnableCaching
public class MoodHolicApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoodHolicApplication.class, args);
    }

}
