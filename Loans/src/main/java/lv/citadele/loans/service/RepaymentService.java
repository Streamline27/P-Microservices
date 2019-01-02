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

        BigDecimal monthlyPrincipal  = calculator.getMonthlyPrincipal(loanRequest);
        BigDecimal monthlyCommission = calculator.getMonthlyCommission(loanRequest, interestRate);

        return getRepayments(loanRequest.getTerm(), monthlyPrincipal, monthlyCommission);
    }

    private List<Repayment> getRepayments(int repaymentNumber, BigDecimal monthlyPrincipal, BigDecimal monthlyCommision) {

        List<Repayment> repayments = new ArrayList<>();
        MonthTracer monthTracer = new MonthTracer();

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
