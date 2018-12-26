package lv.citadele.loans.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@AllArgsConstructor
public class RepaymentDTO {
    private Date termDate;
    private BigDecimal principal;
    private BigDecimal commission;
}
