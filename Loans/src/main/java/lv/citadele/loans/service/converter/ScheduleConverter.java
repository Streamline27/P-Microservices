package lv.citadele.loans.service.converter;

import lv.citadele.loans.api.ScheduleDTO;
import lv.citadele.loans.model.Loan;
import lv.citadele.loans.service.AmountCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduleConverter {

    private final RepaymentConverter repaymentConverter;
    private final AmountCalculator calculator;

    @Autowired
    public ScheduleConverter(RepaymentConverter repaymentConverter,
                             AmountCalculator calculator) {
        this.repaymentConverter = repaymentConverter;
        this.calculator = calculator;
    }

    public ScheduleDTO toDto(Loan loan) {
        return new ScheduleDTO(
                loan.getLoanId(),
                loan.getLoanRequestId(),
                loan.getTerm(),
                loan.getInterestRate(),
                calculator.getTotalPrincipal(loan),
                calculator.getTotalCommission(loan),
                repaymentConverter.toDto(loan.getRepayments())
        );
    }
}
