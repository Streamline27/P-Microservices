package lv.citadele.auth.api;

public class TokenResponse {

    private String accessToken;

    public TokenResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    private TokenResponse(Builder builder) {
        this.accessToken = builder.accessToken;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public static final class Builder {
        private String accessToken;

        private Builder() {
        }

        public TokenResponse build() {
            return new TokenResponse(this);
        }

        public Builder accessToken(String accessToken) {
            this.accessToken = accessToken;
            return this;
        }
    }
}
