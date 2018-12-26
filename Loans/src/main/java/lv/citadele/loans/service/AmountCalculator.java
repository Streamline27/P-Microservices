package lv.citadele.loans.service;

import lv.citadele.loans.api.LoanDTO;
import lv.citadele.loans.model.Loan;
import lv.citadele.loans.model.Repayment;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class AmountCalculator {

    public BigDecimal getMonthlyPrincipal(LoanDTO loanRequest) {
        final BigDecimal term = new BigDecimal(loanRequest.getTerm());
        final BigDecimal totalAmount = loanRequest.getAmount();
        return totalAmount.divide(term, 2, RoundingMode.HALF_UP);
    }

    public BigDecimal getMonthlyCommission(LoanDTO loanRequest, BigDecimal interestRate) {
        final BigDecimal term = new BigDecimal(loanRequest.getTerm());
        final BigDecimal totalAmount = loanRequest.getAmount();
        return totalAmount.multiply(interestRate).divide(term, 2, RoundingMode.HALF_UP);
    }

    public BigDecimal getTotalPrincipal(Loan loan) {
        Double sum = loan.getRepayments().stream().mapToDouble(this::getPrincipalValue).sum();
        return new BigDecimal(sum);
    }

    public BigDecimal getTotalCommission(Loan loan) {
        Double sum = loan.getRepayments().stream().mapToDouble(this::getCommissionValue).sum();
        return new BigDecimal(sum);
    }

    private Double getPrincipalValue(Repayment repayment) {
        return repayment.getPrincipal().doubleValue();
    }

    private Double getCommissionValue(Repayment repayment) {
        return repayment.getCommission().doubleValue();
    }
}
