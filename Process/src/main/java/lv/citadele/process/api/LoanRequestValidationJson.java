package lv.citadele.process.api;

import lombok.*;
import lombok.experimental.FieldNameConstants;

import java.util.Set;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoanRequestValidationJson {
    private Long loanRequestId;
    private boolean validated;
    private Set<Problem> validationProblems;

   public enum Problem {
        YEARLY_TURNOVER_TOO_SMALL,
        COMPANY_IS_BLACKLISTED
    }
}
