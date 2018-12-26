package lv.citadele.process.features.util;

import lv.citadele.process.model.LoanRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class MonthlyExpensesValidator {

    private static final int MONTHS_IN_A_YEAR = 12;
    private static final Double INTEREST_RATE = 0.3;

    public boolean monthlyExpensesExceedMaxLimit(LoanRequest loanRequest) {
        if (yearlyTurnOverNotSpecified(loanRequest)) return true;

        final BigDecimal maxAllowedMonthlyLimit = getMaxAllowedMonthlyLimit(loanRequest);
        final BigDecimal monthlyExpenses = getMonthlyExpenses(loanRequest);

        return monthlyExpenses.doubleValue() > maxAllowedMonthlyLimit.doubleValue();
    }

    private BigDecimal getMonthlyExpenses(LoanRequest loanRequest) {
        final BigDecimal amount = loanRequest.getAmount();
        final Integer term = loanRequest.getTerm();
        return amount.divide(BigDecimal.valueOf(term), RoundingMode.HALF_UP);
    }

    private BigDecimal getMaxAllowedMonthlyLimit(LoanRequest loanRequest) {
        final BigDecimal yearTurnover = loanRequest.getYearlyTurnover();
        return yearTurnover
                .divide(BigDecimal.valueOf(MONTHS_IN_A_YEAR), RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(INTEREST_RATE));
    }

    private boolean yearlyTurnOverNotSpecified(LoanRequest loanRequest) {
        return loanRequest.getYearlyTurnover() == null;
    }
}
