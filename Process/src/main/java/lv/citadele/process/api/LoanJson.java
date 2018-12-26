package lv.citadele.process.api;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoanJson {
    private Long loanRequestId;
    private BigDecimal amount;
    private Integer term;
}
