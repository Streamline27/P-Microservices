package lv.citadele.process.api;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ScheduleJson {
    private Long loanId;
    private Long loanRequestId;
    private Integer term;
    private BigDecimal interestRate;
    private BigDecimal totalPrincipal;
    private BigDecimal totalCommission;
    private List<RepaymentJson> repayments;
}
