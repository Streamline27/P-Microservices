package lv.citadele.process.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.stream.Stream;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(indexes = { @Index(name = "IDX_DATE_COMPANY", columnList = "companyRegistrationNumber,createdDate") })
public class LoanRequest {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false) private BigDecimal amount;
    @Column(nullable = false) private String companyRegistrationNumber;
    @Column(nullable = false) private String email;
    @Column(nullable = false) private String phone;
    @Column(nullable = false) private Integer term;

    private BigDecimal yearlyTurnover;
    private String companyName;
    private String companyType;

    @Enumerated(EnumType.STRING)
    private LoanRequestStatus status;

    private Date createdDate;
    private Long loanId;

    public LoanRequest(BigDecimal amount,
                       String companyRegistrationNumber,
                       String email,
                       String phone,
                       BigDecimal yearlyTurnover,
                       Integer term,
                       String companyName,
                       String companyType,
                       LoanRequestStatus status,
                       Date createdDate) {
        this.amount = amount;
        this.companyRegistrationNumber = companyRegistrationNumber;
        this.email = email;
        this.phone = phone;
        this.yearlyTurnover = yearlyTurnover;
        this.term = term;
        this.companyName = companyName;
        this.companyType = companyType;
        this.status = status;
        this.createdDate = createdDate;
    }
}
