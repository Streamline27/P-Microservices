package lv.citadele.auth.service;

import lv.citadele.auth.model.UserCredentialsDao;
import lv.citadele.auth.utils.UserCredentialsWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {

    private final UserCredentialsDao credentialsDao;

//    @Autowired
    public AuthenticationService(UserCredentialsDao credentialsDao) {
        this.credentialsDao = credentialsDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return credentialsDao.findByUsername(username)
                .map(UserCredentialsWrapper::new)
                .orElse(null);
    }
}
