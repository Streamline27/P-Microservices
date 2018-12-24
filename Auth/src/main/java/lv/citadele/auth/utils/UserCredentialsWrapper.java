package lv.citadele.auth.utils;

import lv.citadele.auth.model.UserCredentials;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;

public class UserCredentialsWrapper extends User {
    private final UserCredentials credentials;

    public UserCredentialsWrapper(UserCredentials credentials) {
        super(credentials.getUsername(), credentials.getPassword(), new ArrayList<>());
        this.credentials = credentials;
    }

    public UserCredentials get() {
        return credentials;
    }
}
