package lv.citadele.process.features;

import lombok.extern.slf4j.Slf4j;
import lv.citadele.process.UserException;
import lv.citadele.process.api.ScheduleJson;
import lv.citadele.process.features.util.LoansRemoteAccessorRest;
import lv.citadele.process.model.LoanRequest;
import lv.citadele.process.model.LoanRequestStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

@Slf4j
@Service
public class ConfirmService {

    private final LoansRemoteAccessorRest loansAccessor;
    private final LoanRequestService loanRequestService;

    @Autowired
    public ConfirmService(LoansRemoteAccessorRest loansAccessor, LoanRequestService loanRequestService) {
        this.loansAccessor = loansAccessor;
        this.loanRequestService = loanRequestService;
    }

    public ScheduleJson confirm(Long loanRequestId) throws UserException {
        final LoanRequest loanRequest = this.loanRequestService.findBy(loanRequestId);
        ensureConfirmationAllowed(loanRequest);
        ScheduleJson shedule = loansAccessor.createLoan(loanRequest);
        loanRequestService.setStatus(loanRequest, LoanRequestStatus.CONFIRMED);
        return shedule;
    }

    private void ensureConfirmationAllowed(LoanRequest loanRequest) throws UserException {
        final LoanRequestStatus status = loanRequest.getStatus();
        if (status.confirmationAllowed()) return;

        String message = MessageFormat.format("Confirmation is not allowed for request id [{0}] in status [{1}]", loanRequest.getId(), status);
        log.warn(message);
        throw new UserException(message);
    }
}
