package lv.citadele.process.features;

import lombok.extern.slf4j.Slf4j;
import lv.citadele.process.util.UserException;
import lv.citadele.process.api.LoanRequestValidationJson;
import lv.citadele.process.features.util.MonthlyExpensesValidator;
import lv.citadele.process.model.LoanRequest;
import lv.citadele.process.model.LoanRequestStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
public class ValidationService {

    private final MonthlyExpensesValidator monthlyExpensesValidator;
    private final BlackListService blacklistService;
    private final LoanRequestService loanRequestService;

    @Autowired
    public ValidationService(MonthlyExpensesValidator monthlyExpensesValidator,
                             BlackListService blackListService,
                             LoanRequestService loanRequestService) {

        this.monthlyExpensesValidator = monthlyExpensesValidator;
        this.blacklistService = blackListService;
        this.loanRequestService = loanRequestService;
    }

    public LoanRequestValidationJson validate(Long loanRequestId) throws UserException {
        final LoanRequest loanRequest = loanRequestService.findBy(loanRequestId);
        ensureValidationAllowedFor(loanRequest);

        ProblemReport report = getReportFor(loanRequest);
        boolean isValid = report.requestIsValid();

        if (isValid) loanRequestService.setStatus(loanRequest, LoanRequestStatus.VALIDATED);
        else         loanRequestService.setStatus(loanRequest, LoanRequestStatus.INVALID);

        return LoanRequestValidationJson.builder()
                .loanRequestId(loanRequestId)
                .validated(isValid)
                .validationProblems(report.getProblems())
                .build();
    }

    private ProblemReport getReportFor(LoanRequest loanRequest) {
        ProblemReport report = new ProblemReport();

        if (monthlyExpensesValidator.monthlyExpensesExceedMaxLimit(loanRequest)) {
            report.add(LoanRequestValidationJson.Problem.YEARLY_TURNOVER_TOO_SMALL);
        }
        if (blacklistService.isBlacklisted(loanRequest)) {
            report.add(LoanRequestValidationJson.Problem.COMPANY_IS_BLACKLISTED);
        }
        return report;
    }

    private class ProblemReport {
        private final Set<LoanRequestValidationJson.Problem> problems;

        private ProblemReport() {
            problems = new HashSet<>();
        }

        private void add(LoanRequestValidationJson.Problem problem) {
            problems.add(problem);
        }

        private boolean requestIsValid() {
            return problems.isEmpty();
        }

        private Set<LoanRequestValidationJson.Problem> getProblems() {
            return problems;
        }
    }

    private void ensureValidationAllowedFor(LoanRequest loanRequest) throws UserException {
        final LoanRequestStatus status = loanRequest.getStatus();
        if (status.validationAllowed()) return;

        String message = MessageFormat.format("Validation is not allowed for request id [{0}] in status [{1}]", loanRequest.getId(), status);
        log.warn(message);
        throw new UserException(message);
    }
}
