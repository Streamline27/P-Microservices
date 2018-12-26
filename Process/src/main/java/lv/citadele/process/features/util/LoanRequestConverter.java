package lv.citadele.process.features.util;

import lv.citadele.process.api.LoanRequestJson;
import lv.citadele.process.model.LoanRequest;
import lv.citadele.process.model.LoanRequestStatus;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LoanRequestConverter {

    public LoanRequest toDomain(LoanRequestJson json) {
        return new LoanRequest(
                json.getAmount(),
                json.getCompanyRegistrationNumber(),
                json.getEmail(),
                json.getPhone(),
                json.getYearlyTurnover(),
                json.getTerm(),
                json.getCompanyName(),
                json.getCompanyType()
        );
    }

    public LoanRequestJson toJson(LoanRequest loanRequest) {
        return new LoanRequestJson(
                loanRequest.getId(),
                loanRequest.getAmount(),
                loanRequest.getCompanyRegistrationNumber(),
                loanRequest.getEmail(),
                loanRequest.getPhone(),
                loanRequest.getYearlyTurnover(),
                loanRequest.getTerm(),
                loanRequest.getCompanyName(),
                loanRequest.getCompanyType(),
                loanRequest.getStatus().name()
        );
    }
}
