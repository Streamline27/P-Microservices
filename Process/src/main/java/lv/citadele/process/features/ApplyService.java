package lv.citadele.process.features;

import lombok.extern.slf4j.Slf4j;
import lv.citadele.process.api.LoanRequestJson;
import lv.citadele.process.features.util.LoanRequestConverter;
import lv.citadele.process.model.LoanRequest;
import lv.citadele.process.model.LoanRequestStatus;
import lv.citadele.process.model.dao.LoanRequestRepository;
import lv.citadele.process.util.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Date;

@Service
@Slf4j
public class ApplyService {

    private final LoanRequestConverter converter;
    private final LoanRequestRepository repository;
    private final BlackListService blackListService;

    @Autowired
    public ApplyService(LoanRequestConverter converter,
                        LoanRequestRepository repository,
                        BlackListService blackListService) {
        this.converter = converter;
        this.repository = repository;
        this.blackListService = blackListService;
    }

    public LoanRequestJson apply(LoanRequestJson json) throws UserException {

        LoanRequest loanRequest = converter.toDomain(json).toBuilder()
                .createdDate(new Date())
                .status(LoanRequestStatus.PENDING)
                .build();

        ensureNotBlacklisted(loanRequest);
        LoanRequest result = repository.save(loanRequest);

        blackListService.blacklistCompanyIfNeeded(loanRequest);

        return converter.toJson(result);
    }

    private void ensureNotBlacklisted(LoanRequest loanRequest) throws UserException {
        if (blackListService.isBlacklisted(loanRequest)) {
            String message = MessageFormat.format("Company [{0}] is blacklisted", loanRequest.getCompanyRegistrationNumber());
            log.warn(message);
            throw new UserException(message);
        }
    }

}
