package lv.citadele.loans.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Loan {
    @Id
    @GeneratedValue
    private Long loanId;
    private Long loanRequestId;

    private BigDecimal interestRate;
    private Integer term;
    private Date startDate;

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "loan"
    )
    private List<Repayment> repayments;

    private Loan(Long loanId,
                 Long loanRequestId,
                 BigDecimal interestRate,
                 Integer term,
                 Date startDate,
                 List<Repayment> repayments) {
        this.loanId = loanId;
        this.loanRequestId = loanRequestId;
        this.interestRate = interestRate;
        this.term = term;
        this.startDate = startDate;
        this.repayments = repayments;
        repayments.forEach(repayment -> repayment.setLoan(this));
    }

    private Loan() {
    }

    public static LoanBuilder builder() {
        return new LoanBuilder();
    }

    public static class LoanBuilder {
        private Long loanId;
        private Long loanRequestId;
        private BigDecimal interestRate;
        private Integer term;
        private Date startDate;
        private List<Repayment> repayments;

        LoanBuilder() {
        }

        public LoanBuilder loanId(Long loanId) {
            this.loanId = loanId;
            return this;
        }

        public LoanBuilder loanRequestId(Long loanRequestId) {
            this.loanRequestId = loanRequestId;
            return this;
        }

        public LoanBuilder interestRate(BigDecimal interestRate) {
            this.interestRate = interestRate;
            return this;
        }

        public LoanBuilder term(Integer term) {
            this.term = term;
            return this;
        }

        public LoanBuilder startDate(Date startDate) {
            this.startDate = startDate;
            return this;
        }

        public LoanBuilder repayments(List<Repayment> repayments) {
            this.repayments = repayments;
            return this;
        }

        public Loan build() {
            return new Loan(loanId, loanRequestId, interestRate, term, startDate, repayments);
        }
    }
}
