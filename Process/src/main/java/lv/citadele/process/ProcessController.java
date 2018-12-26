package lv.citadele.process;

import io.swagger.annotations.ApiOperation;
import lv.citadele.process.api.LoanRequestJson;
import lv.citadele.process.api.LoanRequestValidationJson;
import lv.citadele.process.api.ScheduleJson;
import lv.citadele.process.features.*;
import lv.citadele.process.features.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProcessController implements ProcessSpecification {

    private final ValidationService validationService;
    private final LoanRequestService loanRequestService;
    private final ApplyService applyService;
    private final ConfirmService confirmService;
    private final RejectService rejectService;

    @Autowired
    public ProcessController(ValidationService validationService,
                             LoanRequestService loanRequestService,
                             ApplyService applyService, ConfirmService confirmService, RejectService rejectService) {

        this.validationService = validationService;
        this.loanRequestService = loanRequestService;
        this.applyService = applyService;
        this.confirmService = confirmService;
        this.rejectService = rejectService;
    }

    @PatchMapping("/requests/{id}/validate")
    public LoanRequestValidationJson validate(@PathVariable("id") Long loanRequestId) throws UserException {
        return validationService.validate(loanRequestId);
    }

    @PatchMapping("/requests/{id}/reject")
    public LoanRequestJson reject(@PathVariable("id") Long loanRequestId) throws UserException {
        return rejectService.reject(loanRequestId);
    }

    @PatchMapping("/requests/{id}/confirm")
    public ScheduleJson confirm(@PathVariable("id") Long loanRequestId) throws UserException {
        return confirmService.confirm(loanRequestId);
    }

    @GetMapping("/requests")
    public List<LoanRequestJson> getAll() {
        return loanRequestService.getAll();
    }

    @PostMapping("/requests")
    public LoanRequestJson apply(@RequestBody @Validated LoanRequestJson loanRequestJson) throws UserException {
        return applyService.apply(loanRequestJson);
    }
}
