package lv.citadele.auth.api;

public class RegistrationRequest {
    private String username;
    private String password;
    private String repeatedPassword;
    private String name;

    public RegistrationRequest() {
    }

    private RegistrationRequest(Builder builder) {
        this.username = builder.username;
        this.password = builder.password;
        this.repeatedPassword = builder.repeatedPassword;
        this.name = builder.name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatedPassword() {
        return repeatedPassword;
    }

    public void setRepeatedPassword(String repeatedPassword) {
        this.repeatedPassword = repeatedPassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String username;
        private String password;
        private String repeatedPassword;
        private String name;

        private Builder() {
        }

        public RegistrationRequest build() {
            return new RegistrationRequest(this);
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder repeatedPassword(String repeatedPassword) {
            this.repeatedPassword = repeatedPassword;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }
    }
}
