package lv.citadele.process.api;

import io.swagger.annotations.ApiModelProperty;
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

    @ApiModelProperty(example = "1")
    private Long loanId;

    @ApiModelProperty(example = "1")
    private Long loanRequestId;

    @ApiModelProperty(example = "6")
    private Integer term;

    @ApiModelProperty(example = "0.03")
    private BigDecimal interestRate;

    @ApiModelProperty(example = "3000")
    private BigDecimal totalPrincipal;

    @ApiModelProperty(example = "90")
    private BigDecimal totalCommission;

    private List<RepaymentJson> repayments;
}
