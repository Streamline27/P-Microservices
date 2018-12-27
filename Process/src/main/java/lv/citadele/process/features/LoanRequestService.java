package lv.citadele.process.features;

import lv.citadele.process.util.UserException;
import lv.citadele.process.api.LoanRequestJson;
import lv.citadele.process.features.util.LoanRequestConverter;
import lv.citadele.process.model.LoanRequest;
import lv.citadele.process.model.LoanRequestStatus;
import lv.citadele.process.model.dao.LoanRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LoanRequestService {

    private final LoanRequestConverter converter;
    private final LoanRequestRepository repository;

    @Autowired
    public LoanRequestService(LoanRequestConverter converter,
                              LoanRequestRepository repository) {
        this.converter = converter;
        this.repository = repository;
    }

    public List<LoanRequestJson> getAll() {
        return repository.findAll().stream()
                .map(converter::toJson)
                .collect(Collectors.toList());
    }

    LoanRequest findBy(Long loanRequestId) throws UserException {
        Optional<LoanRequest> loanRequest = repository.findById(loanRequestId);
        if (loanRequest.isPresent()) {
            return loanRequest.get();
        }
        throw new UserException(MessageFormat.format("Loan request with id [{0}] does not exist", loanRequestId));
    }

    LoanRequest setStatus(LoanRequest loanRequest, LoanRequestStatus status) {
        LoanRequest validatedRequest = loanRequest.toBuilder()
                .status(status)
                .build();

        return repository.save(validatedRequest);
    }
}
