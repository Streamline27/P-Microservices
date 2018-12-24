package lv.citadele.auth.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserCredentialsDao extends CrudRepository<UserCredentials, Long> {
    Optional<UserCredentials> findByUsername(String username);
    Optional<UserCredentials> findByUsernameAndPassword(String username, String password);
}
