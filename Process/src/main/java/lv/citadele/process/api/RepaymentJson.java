package lv.citadele.process.api;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RepaymentJson {
    private Date termDate;

    @ApiModelProperty(example = "500")
    private BigDecimal principal;

    @ApiModelProperty(example = "15")
    private BigDecimal commission;
}
