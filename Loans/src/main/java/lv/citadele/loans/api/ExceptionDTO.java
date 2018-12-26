package lv.citadele.loans.api;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExceptionDTO {
    public String message;
}
