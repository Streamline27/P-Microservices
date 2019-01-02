package lv.citadele.zuul.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoanRequestModel {
    private Long id;
    private BigDecimal amount;
    private String companyRegistrationNumber;
    private String email;
    private String phone;
    private BigDecimal yearlyTurnover;
    private Integer term = 6;
    private String companyName;
    private String companyType;
    private String status;
}
