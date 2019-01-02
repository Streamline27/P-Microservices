package lv.citadele.zuul.steps;

import cucumber.api.Transpose;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import lv.citadele.zuul.dto.LoanRequestModel;

import java.util.List;

public class LoanRequestStepDefs {

    private final LoanRequestSteps steps;

    public LoanRequestStepDefs(LoanRequestSteps steps) {
        this.steps = steps;
    }

    @When("^user creates loan request with following details:$")
    public void userCreatesLoanRequests(@Transpose List<LoanRequestModel> loanRequest) {
        steps.userCreatesLoanRequests(loanRequest.get(0));
    }

    @When("^validates it$")
    public void userValidatesCreatedLoanRequest() {
        steps.userValidatesCreatedLoanRequest();
    }

    @When("^confirms validated request$")
    public void userConfirmsValidatedLoanRequest() {
        steps.userConfirmsLoanRequest();
    }

    @When("^gets created loan$")
    public void userGetsCreatedLoan() {
        steps.userGetsCreatedLoan();
    }

    @Then("^loan contains request id$")
    public void loanContainsRequestId() {
        steps.loanIsCreated();
    }

    @Then("^response shows that request is invalid$")
    public void responseShowsThatRequestIsInvalid() {
        steps.requestIsInvalid();
    }

}
