package lv.citadele.loans.service;

import lv.citadele.loans.api.LoanDTO;
import lv.citadele.loans.model.Repayment;
import org.joda.time.DateTimeZone;
import org.joda.time.MutableDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RepaymentService {

    private final AmountCalculator calculator;

    @Autowired
    public RepaymentService(AmountCalculator calculator) {
        this.calculator = calculator;
    }

    public List<Repayment> computeFor(LoanDTO loanRequest, BigDecimal interestRate) {
        final int term = loanRequest.getTerm();
        final BigDecimal monthlyPrincipal  = calculator.getMonthlyPrincipal(loanRequest);
        final BigDecimal monthlyCommission = calculator.getMonthlyCommission(loanRequest, interestRate);

        return getRepayments(term, monthlyPrincipal, monthlyCommission);
    }

    private List<Repayment> getRepayments(int repaymentNumber, BigDecimal monthlyPrincipal, BigDecimal monthlyCommision) {
        MonthTracer monthTracer = new MonthTracer();
        List<Repayment> repayments = new ArrayList<>();
        for (int i = 0; i < repaymentNumber; i++) {
            monthTracer.incrementMonth();
            repayments.add(
                    new Repayment(
                            monthTracer.getDate(),
                            monthlyPrincipal,
                            monthlyCommision
                    )
            );
        }
        return repayments;
    }

    private final class MonthTracer {
        final MutableDateTime dateTime;

        private MonthTracer() {
            this.dateTime = new MutableDateTime();
            dateTime.setDayOfMonth(1);
            dateTime.setMillis(0);
            dateTime.setZone(DateTimeZone.UTC);
        }

        private void incrementMonth() {
            dateTime.addMonths(1);
        }

        private Date getDate() {
            return dateTime.toDate();
        }
    }
}
