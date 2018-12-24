package lv.citadele.auth.utils;

public class UserException extends Exception {

    private final ExceptionCode exceptionCode;

    public UserException(ExceptionCode exceptionCode) {
        this.exceptionCode = exceptionCode;
    }

    public ExceptionCode getExceptionCode() {
        return exceptionCode;
    }
}
