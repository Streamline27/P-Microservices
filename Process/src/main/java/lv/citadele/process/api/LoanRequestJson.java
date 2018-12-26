package lv.citadele.process.api;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.FieldNameConstants;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoanRequestJson {

    private Long id;

    @NotNull
    @ApiModelProperty(example = "3000")
    private BigDecimal amount;

    @NotEmpty
    @ApiModelProperty(example = "LV10111")
    private String companyRegistrationNumber;

    @Email
    @ApiModelProperty(example = "eminem@gmail.com")
    private String email;

    @NotEmpty
    @ApiModelProperty(example = "2000006")
    private String phone;

    @ApiModelProperty(example = "30000")
    private BigDecimal yearlyTurnover;

    @Min(1)
    @Max(12)
    @ApiModelProperty(example = "6")
    private Integer term = 6;

    @ApiModelProperty(example = "E-Ziedi")
    private String companyName;

    @ApiModelProperty(example = "Ziedu piegade")
    private String companyType;

    @ApiModelProperty(example = "PENDING")
    private String status;
}
