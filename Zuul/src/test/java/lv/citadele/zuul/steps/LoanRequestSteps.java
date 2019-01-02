package lv.citadele.zuul.steps;

import lv.citadele.zuul.dto.LoanRequestModel;
import lv.citadele.zuul.ApiClient;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;

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

    public void requestIsInvalid() {
        assertFalse(context.isValidated());
    }

    public void userGetsCreatedLoan() {
        Long savedLoanRequestId = client.getLoan(context.getLoanId())
                .extract()
                .jsonPath()
                .getLong("loanRequestId");

        context.setSavedRequestId(savedLoanRequestId);
    }

    public void userConfirmsLoanRequest() {
        Long loanId = client.confirmRequest(context.getRequestId())
                .extract()
                .jsonPath()
                .getLong("loanId");

        context.setLoanId(loanId);
    }

    public void userValidatesCreatedLoanRequest() {
        boolean validated = client.validateRequest(context.getRequestId())
            .extract()
            .jsonPath()
            .getBoolean("validated");

        context.setValidated(validated);
    }

    public void userCreatesLoanRequests(LoanRequestModel loanRequest) {
        Long loanRequestId = client.createRequest(loanRequest)
                .extract()
                .jsonPath()
                .getLong("id");

        context.setRequestId(loanRequestId);
    }
}
