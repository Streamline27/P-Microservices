package lv.citadele.auth;

import lv.citadele.auth.api.RegistrationRequest;
import lv.citadele.auth.api.TokenResponse;
import lv.citadele.auth.model.UserCredentials;
import lv.citadele.auth.service.RegistrationService;
import lv.citadele.auth.service.TokenService;
import lv.citadele.auth.utils.UserCredentialsWrapper;
import lv.citadele.auth.utils.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

@RestController
public class AuthController {

    private final RegistrationService registrationService;
    private final TokenService tokenService;

    @Autowired
    public AuthController(RegistrationService registrationService, TokenService tokenService) {
        this.registrationService = registrationService;
        this.tokenService = tokenService;
    }

    @PostMapping("/token")
    public ResponseEntity<TokenResponse> getToken(Authentication auth) {
        UserCredentials credentials = extractCredentialsFrom(auth);
        TokenResponse response = tokenService.getFor(credentials);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/registration")
    public ResponseEntity getToken(@RequestBody RegistrationRequest request) throws UserException {
        registrationService.process(request);
        return ResponseEntity.ok()
                .build();
    }

    private UserCredentials extractCredentialsFrom(Authentication authentication) {
        return ((UserCredentialsWrapper) authentication.getPrincipal()).get();
    }
}
