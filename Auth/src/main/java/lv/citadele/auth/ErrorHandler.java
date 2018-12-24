package lv.citadele.auth;

import lv.citadele.auth.utils.ExceptionCode;
import lv.citadele.auth.api.ExceptionResponse;
import lv.citadele.auth.utils.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(UserException.class)
    public ResponseEntity handleException(UserException exc) {
        ExceptionResponse response = toDto(exc.getExceptionCode());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    public ExceptionResponse toDto(ExceptionCode code) {
        return ExceptionResponse.builder()
                .message(code.getMessage())
                .code(code.name())
                .build();
    }
}
