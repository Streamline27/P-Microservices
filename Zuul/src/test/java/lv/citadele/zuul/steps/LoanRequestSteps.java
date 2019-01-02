package lv.citadele.zuul.steps;

import lv.citadele.zuul.dto.LoanRequestModel;
import lv.citadele.zuul.ApiClient;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class LoanRequestSteps {

    private final Context context;
    private final ApiClient client;

    public LoanRequestSteps(Context context, ApiClient client) {
        this.context = context;
        this.client = client;
    }

    public void loanIsCreated() {
        assertThat(context.getRequestId(), is(context.getSavedRequestId()));
    }

    public void userGetsCreatedLoan() {
        Long savedLoanRequestId = client.getLoan(context.getLoanId())
                .extract()
                .jsonPath()
                .getLong("loanRequestId");

        context.setSavedRequestId(savedLoanRequestId);
    }

    public void userConfirmsValidatedLoanRequest() {
        Long loanId = client.confirmRequest(context.getRequestId())
                .extract()
                .jsonPath()
                .getLong("loanId");

        context.setLoanId(loanId);
    }

    public void userValidatesCreatedLoanRequest() {
        client.validateRequest(context.getRequestId());
    }

    public void userCreatesLoanRequests() {
        Long loanRequestId = client.createRequest(getLoanRequest())
                .extract()
                .jsonPath()
                .getLong("id");

        context.setRequestId(loanRequestId);
    }

    private LoanRequestModel getLoanRequest() {
        return LoanRequestModel.builder()
                .yearlyTurnover(new BigDecimal(300000))
                .companyRegistrationNumber("LV-1063")
                .amount(new BigDecimal(3000))
                .email("company@company.com")
                .companyName("Vasjas Inc.")
                .phone("203485748")
                .term(6)
                .build();
    }
}
