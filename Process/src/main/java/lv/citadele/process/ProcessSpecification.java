package lv.citadele.process;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lv.citadele.process.api.LoanRequestJson;
import lv.citadele.process.api.LoanRequestValidationJson;
import lv.citadele.process.api.ScheduleJson;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Api(description = "Loan application specific functionality")
public interface ProcessSpecification {

    @ApiOperation(value = "Get instance name")
    String ping();

    @ApiOperation(value = "Get all requests")
    List<LoanRequestJson> getAll() throws UserException;

    @ApiOperation(value = "Apply for loan")
    LoanRequestJson apply(@RequestBody @Validated LoanRequestJson loanRequestJson) throws UserException;

    @ApiOperation(value = "Confirm loan application")
    ScheduleJson confirm(@PathVariable("id") Long loanRequestId) throws UserException;

    @ApiOperation(value = "Reject loan application")
    LoanRequestJson reject(@PathVariable("id") Long loanRequestId) throws UserException;

    @ApiOperation(value = "Validate loan application")
    LoanRequestValidationJson validate(@PathVariable("id") Long loanRequestId) throws UserException;
}
