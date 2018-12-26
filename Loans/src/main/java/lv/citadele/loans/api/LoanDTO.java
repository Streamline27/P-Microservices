package lv.citadele.loans.api;

import io.swagger.annotations.ApiModelProperty;
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

    @ApiModelProperty(example = "1")
    @NotNull private Long loanRequestId;

    @ApiModelProperty(example = "3000")
    @NotNull private BigDecimal amount;

    @ApiModelProperty(example = "6")
    @NotNull @Min(1) @Max(12)
    private Integer term = 6;
}
