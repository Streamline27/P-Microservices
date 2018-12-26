package lv.citadele.loans.api;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@AllArgsConstructor
public class ScheduleDTO {
    private Long loanId;
    private Long loanRequestId;
    private Integer term;
    private BigDecimal interestRate;
    private BigDecimal totalPrincipal;
    private BigDecimal totalCommission;
    private List<RepaymentDTO> repayments;
}
