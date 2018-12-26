package lv.citadele.loans.api;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoanDTO {
    @NotNull private Long loanRequestId;
    @NotNull private BigDecimal amount;

    @NotNull @Min(1) @Max(12)
    private Integer term = 6;
}
