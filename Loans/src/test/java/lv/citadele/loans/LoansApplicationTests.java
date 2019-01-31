package lv.citadele.loans;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.Entity;
import java.io.IOException;

@RunWith(MockitoJUnitRunner.class)
public class LoansApplicationTests {

    @Test
    public void x() throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        Login user = Login.builder()
                .name("VladJa")
                .surname("Jakushin")
                .build();

        String s = mapper.writeValueAsString(user);
        System.out.println(s);

        Login user1 = mapper.readValue(s, Login.class);

        System.out.println(user1);
    }

    @Data
    @Builder
    @Setter(AccessLevel.NONE)
    static class User {
        private final String name;
        private final String surname;
    }

    /**
     * lombok.config -> lombok.anyConstructor.addConstructorProperties = true
     */
    @Entity
    @Builder
    static class Login {
        private final String name;
        private final String surname;
    }
}

