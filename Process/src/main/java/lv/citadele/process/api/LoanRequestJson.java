package lv.citadele.process.api;

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
    private BigDecimal amount;
    @NotEmpty
    private String companyRegistrationNumber;
    @Email
    private String email;
    @NotEmpty
    private String phone;
    private BigDecimal yearlyTurnover;
    @Min(1) @Max(12)
    private Integer term = 6;
    private String companyName;
    private String companyType;
    private String status;
}
