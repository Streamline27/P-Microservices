package lv.citadele.auth.api;

public class ExceptionResponse {
    private String message;
    private String code;

    private ExceptionResponse() {
    }

    private ExceptionResponse(Builder builder) {
        this.message = builder.message;
        this.code = builder.code;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public static final class Builder {
        private String message;
        private String code;

        private Builder() {
        }

        public ExceptionResponse build() {
            return new ExceptionResponse(this);
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder code(String code) {
            this.code = code;
            return this;
        }
    }
}
