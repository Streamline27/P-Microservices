package lv.citadele.loans.service;

import lombok.extern.slf4j.Slf4j;
import lv.citadele.loans.util.UserException;
import lv.citadele.loans.api.LoanDTO;
import lv.citadele.loans.api.ScheduleDTO;
import lv.citadele.loans.service.converter.ScheduleConverter;
import lv.citadele.loans.model.Loan;
import lv.citadele.loans.model.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.MessageFormat;
import java.util.Date;

@Slf4j
@Service
public class LoanService {

    private final static BigDecimal INTEREST_RATE = new BigDecimal(0.3).setScale(1, RoundingMode.HALF_UP);

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

        Loan loan = Loan.builder()
                .loanRequestId(loanRequest.getLoanRequestId())
                .interestRate(INTEREST_RATE)
                .repayments(repaymentService.computeFor(loanRequest, INTEREST_RATE))
                .term(loanRequest.getTerm())
                .startDate(new Date())
                .build();

        Loan result = loanRepository.save(loan);
        log.info("Created loan. id: [{}], requestId: [{}]", result.getLoanId(), result.getLoanRequestId());

        return converter.toDto(result);
    }

    public ScheduleDTO get(Long loanId) throws UserException {
        return loanRepository.findById(loanId).map(converter::toDto)
                .orElseThrow(() ->
                        new UserException(getMessage(loanId))
                );
    }

    private String getMessage(Long loanId) {
        return MessageFormat.format("Loan with id [{0}] not found", loanId);
    }
}
