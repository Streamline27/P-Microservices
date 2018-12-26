package lv.citadele.loans;

import lv.citadele.loans.api.ExceptionDTO;
import lv.citadele.loans.api.LoanDTO;
import lv.citadele.loans.api.ScheduleDTO;
import lv.citadele.loans.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@ControllerAdvice
public class LoanController {

    private final LoanService loanService;

    @Autowired
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping("/loan")
    public ScheduleDTO hello(@RequestBody LoanDTO loanRequest) {
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
