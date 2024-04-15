package akatsuki.moodholic.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.MissingRequestHeaderException;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);


    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<String> handleMissingRequestHeaderException(MissingRequestHeaderException ex) {
        // 로거를 사용하여 서버 로그에 기록
        logger.warn("Missing header: " + ex.getHeaderName());
        // 클라이언트에게 누락된 헤더에 대한 정보를 반환
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Request is missing mandatory HTTP header: " + ex.getHeaderName());
    }
}
