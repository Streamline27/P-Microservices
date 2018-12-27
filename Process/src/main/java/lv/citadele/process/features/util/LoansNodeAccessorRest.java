package lv.citadele.process.features.util;

import lv.citadele.process.api.LoanJson;
import lv.citadele.process.api.ScheduleJson;
import lv.citadele.process.model.LoanRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LoansNodeAccessorRest {

    private final RestTemplate restTemplate;

    @Autowired
    public LoansNodeAccessorRest(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ScheduleJson createLoan(LoanRequest loanRequest) {

        LoanJson loanJson = LoanJson.builder()
                .loanRequestId(loanRequest.getId())
                .term(loanRequest.getTerm())
                .amount(loanRequest.getAmount())
                .build();

        return restTemplate.postForObject("http://loans-node/loans-node/loans", loanJson, ScheduleJson.class);
    }
}
