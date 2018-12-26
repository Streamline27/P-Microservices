package lv.citadele.loans;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lv.citadele.loans.api.LoanDTO;
import lv.citadele.loans.api.ScheduleDTO;
import lv.citadele.loans.util.UserException;
import org.springframework.web.bind.annotation.RequestBody;

@Api(description = "Loan service related functionality")
public interface LoansSpecification {

    @ApiOperation(value = "Get instance name")
    String ping();

    @ApiOperation(value = "Get loan scheduler")
    ScheduleDTO get(Long loanId) throws UserException;

    @ApiOperation(value = "Create new loan")
    ScheduleDTO create(@RequestBody LoanDTO loanRequest);
}
