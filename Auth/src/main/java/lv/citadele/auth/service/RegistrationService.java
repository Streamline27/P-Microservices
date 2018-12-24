package lv.citadele.auth.service;

import lv.citadele.auth.utils.ExceptionCode;
import lv.citadele.auth.api.RegistrationRequest;
import lv.citadele.auth.model.UserCredentials;
import lv.citadele.auth.model.UserCredentialsDao;
import lv.citadele.auth.utils.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    private final UserCredentialsDao dao;
    private final PasswordEncoder encoder;

    @Autowired
    public RegistrationService(UserCredentialsDao dao, PasswordEncoder encoder) {
        this.dao = dao;
        this.encoder = encoder;
    }

    public void process(RegistrationRequest request) throws UserException {
        final String password = request.getPassword();
        final String repeatedPassword = request.getRepeatedPassword();
        final String username = request.getUsername();
        final String name = request.getName();

        ensureEquals(password, repeatedPassword);
        ensureUnique(username);

        UserCredentials user = UserCredentials.builder()
                .role(UserCredentials.Role.CLIENT)
                .username(username)
                .password(encoder.encode(password))
                .name(name)
                .build();

        dao.save(user);
    }

    private void ensureEquals(String password, String repeatedPassword) throws UserException {
        if (!password.equals(repeatedPassword)) {
            throw new UserException(ExceptionCode.EXC_PASSWORD_MISMATCH);
        }
    }

    private void ensureUnique(String username) throws UserException {
        if (dao.findByUsername(username).isPresent()) {
            throw new UserException(ExceptionCode.EXC_BAD_USERNAME);
        }
    }
}
