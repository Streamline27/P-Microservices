package lv.citadele.loans;

import lv.citadele.loans.api.ExceptionDTO;
import lv.citadele.loans.api.LoanDTO;
import lv.citadele.loans.api.ScheduleDTO;
import lv.citadele.loans.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@ControllerAdvice
public class LoanController implements LoansSpecification {

    @Value("${micro-service.instance-name:LoanInstanceUNDEFINED}")
    private String INSTANCE_NAME;

    private final LoanService loanService;

    @Autowired
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping("/ping")
    public String ping() {
        return INSTANCE_NAME;
    }

    @PostMapping("/loan")
    public ScheduleDTO create(@RequestBody LoanDTO loanRequest) {
        return loanService.create(loanRequest);
    }

    @GetMapping("/loan/{loanId}")
    public ScheduleDTO get(@PathVariable("loanId") Long loanId) throws UserException {
        return loanService.get(loanId);
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity handleException(UserException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionDTO(ex.getMessage()));
    }
}
