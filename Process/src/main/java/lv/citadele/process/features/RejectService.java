package lv.citadele.process.features;

import lombok.extern.slf4j.Slf4j;
import lv.citadele.process.UserException;
import lv.citadele.process.api.LoanRequestJson;
import lv.citadele.process.features.util.LoanRequestConverter;
import lv.citadele.process.model.LoanRequest;
import lv.citadele.process.model.LoanRequestStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

@Slf4j
@Service
public class RejectService {

    private final LoanRequestConverter converter;
    private final LoanRequestService loanRequestService;

    @Autowired
    public RejectService(LoanRequestConverter converter, LoanRequestService loanRequestService) {
        this.converter = converter;
        this.loanRequestService = loanRequestService;
    }

    public LoanRequestJson reject(Long loanRequestId) throws UserException {
        final LoanRequest loanRequest = loanRequestService.findBy(loanRequestId);
        ensureRejectAllowedFor(loanRequest);
        LoanRequest result = loanRequestService.setStatus(loanRequest, LoanRequestStatus.REJECTED);
        return converter.toJson(result);
    }

    private void ensureRejectAllowedFor(LoanRequest loanRequest) throws UserException {
        final LoanRequestStatus status = loanRequest.getStatus();
        if (status.rejectAllowed()) return;

        String message = MessageFormat.format("Reject is not allowed for request id [{0}] in status [{1}]", loanRequest.getId(), status);
        log.warn(message);
        throw new UserException(message);
    }
}
