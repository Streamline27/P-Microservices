package lv.citadele.process.features;

import lombok.extern.slf4j.Slf4j;
import lv.citadele.process.model.LoanRequest;
import lv.citadele.process.model.dao.LoanRequestRepository;
import lv.citadele.process.model.dao.BlackListRepository;
import lv.citadele.process.model.Company;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class BlackListService {

    @Value("blacklistService.num-requests-per-min")
    private Long MAX_COMPANY_REQUESTS_PER_MINUTE;

    private final LoanRequestRepository loanRequestRepository;
    private final BlackListRepository blackListRepository;

    @Autowired
    public BlackListService(LoanRequestRepository loanRequestRepository, BlackListRepository blackListRepository) {
        this.loanRequestRepository = loanRequestRepository;
        this.blackListRepository = blackListRepository;
    }

    void blacklistCompanyIfNeeded(LoanRequest loanRequest) {
        final String companyRegistrationNumber = loanRequest.getCompanyRegistrationNumber();
        final Long numRequestsInLastMinute = numRequestsInLastMinuteFor(companyRegistrationNumber);

        if (numRequestsInLastMinute >= MAX_COMPANY_REQUESTS_PER_MINUTE) {
            blacklist(companyRegistrationNumber);
        }
    }

    boolean isBlacklisted(LoanRequest loanRequest) {
        final String registrationNumber = loanRequest.getCompanyRegistrationNumber();
        return blackListRepository.findById(registrationNumber).isPresent();
    }

    private Long numRequestsInLastMinuteFor(String registrationNumber) {
        final Date now = new Date();
        final Date momentOneMinAgo = DateUtils.addMinutes(now, -1);

        return loanRequestRepository.countAllByCompanyRegistrationNumberAndCreatedDateBetween(
                registrationNumber,
                momentOneMinAgo,
                now
        );
    }

    private void blacklist(String companyRegistrationNumber) {
        blackListRepository.save(new Company(companyRegistrationNumber, new Date()));
        log.warn("Company [{}] got blacklisted", companyRegistrationNumber);
    }
}
