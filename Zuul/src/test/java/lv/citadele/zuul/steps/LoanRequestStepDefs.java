package lv.citadele.zuul.steps;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LoanRequestStepDefs {

    private final LoanRequestSteps steps;

    public LoanRequestStepDefs(LoanRequestSteps steps) {
        this.steps = steps;
    }

    @When("^user creates loan request$")
    public void userCreatesLoanRequests() {
        steps.userCreatesLoanRequests();
    }

    @When("^validates it$")
    public void userValidatesCreatedLoanRequest() {
        steps.userValidatesCreatedLoanRequest();
    }

    @When("^confirms validated request$")
    public void userConfirmsValidatedLoanRequest() {
        steps.userConfirmsValidatedLoanRequest();
    }

    @When("^gets created loan$")
    public void userGetsCreatedLoan() {
        steps.userGetsCreatedLoan();
    }

    @Then("^loan contains request id$")
    public void loanContainsRequestId() {
        steps.loanIsCreated();
    }

}
