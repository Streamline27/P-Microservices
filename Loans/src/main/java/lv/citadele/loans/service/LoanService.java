package lv.citadele.loans.service;

import lv.citadele.loans.UserException;
import lv.citadele.loans.api.LoanDTO;
import lv.citadele.loans.api.ScheduleDTO;
import lv.citadele.loans.service.converter.ScheduleConverter;
import lv.citadele.loans.model.Loan;
import lv.citadele.loans.model.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.Date;

@Service
public class LoanService {

    private final static BigDecimal INTEREST_RATE = new BigDecimal(0.3);

    private final RepaymentService repaymentService;
    private final LoanRepository loanRepository;
    private final ScheduleConverter converter;

    @Autowired
    public LoanService(RepaymentService repaymentService, LoanRepository loanRepository, ScheduleConverter converter) {
        this.repaymentService = repaymentService;
        this.loanRepository = loanRepository;
        this.converter = converter;
    }

    public ScheduleDTO create(LoanDTO loanRequest) {
        final Integer term = loanRequest.getTerm();
        final Date now = new Date();

        Loan loan = Loan.builder()
                .loanRequestId(loanRequest.getLoanRequestId())
                .interestRate(INTEREST_RATE)
                .repayments(repaymentService.computeFor(loanRequest, INTEREST_RATE))
                .startDate(now)
                .term(term)
                .build();

        Loan result = loanRepository.save(loan);
        return converter.toDto(result);
    }

    public ScheduleDTO get(Long loanId) throws UserException {
        return loanRepository.findById(loanId).map(converter::toDto)
                .orElseThrow(() ->
                        new UserException(MessageFormat.format("Loan with id [{0}] not found", loanId))
                );
    }
}
