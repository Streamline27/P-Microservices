package lv.citadele.auth.utils;

public enum ExceptionCode {
    EXC_BAD_USERNAME("Username already taken."),
    EXC_PASSWORD_MISMATCH("Repeated password does not match."),
    EXC_UNAUTHORIZED("Unauthorized."),
    EXC_AUTHENTICATION_FAILED("Bad credentials.");

    private final String message;

    ExceptionCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
