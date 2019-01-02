package lv.citadele.process.features.util;

import lv.citadele.process.model.LoanRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class MonthlyExpensesValidator {

    private static final BigDecimal MONTHS_IN_A_YEAR = new BigDecimal(12);
    private static final BigDecimal INTEREST_RATE = new BigDecimal(0.3);

    public boolean monthlyExpensesExceedMaxLimit(LoanRequest loanRequest) {
        if (yearlyTurnoverNotSpecified(loanRequest)) return true;

        final BigDecimal maxAllowedMonthlyLimit = getMaxAllowedMonthlyLimit(loanRequest);
        final BigDecimal monthlyExpenses = getMonthlyExpenses(loanRequest);

        return monthlyExpenses.doubleValue() > maxAllowedMonthlyLimit.doubleValue();
    }

    private BigDecimal getMonthlyExpenses(LoanRequest loanRequest) {
        final BigDecimal amount = loanRequest.getAmount();
        final BigDecimal term = new BigDecimal(loanRequest.getTerm());

        return amount.divide(term, RoundingMode.HALF_UP);
    }

    private BigDecimal getMaxAllowedMonthlyLimit(LoanRequest loanRequest) {
        final BigDecimal yearTurnover = loanRequest.getYearlyTurnover();

        return yearTurnover
                .divide(MONTHS_IN_A_YEAR, RoundingMode.HALF_UP)
                .multiply(INTEREST_RATE);
    }

    private boolean yearlyTurnoverNotSpecified(LoanRequest loanRequest) {
        return loanRequest.getYearlyTurnover() == null;
    }
}
