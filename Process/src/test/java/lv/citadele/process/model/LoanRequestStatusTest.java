package lv.citadele.process.model;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class LoanRequestStatusTest {

    @Test
    public void createFrom_ForExistingValue_ReturnsThatValue() {
        LoanRequestStatus status = LoanRequestStatus.createFrom("PENDING");
        assertThat(status, is(LoanRequestStatus.PENDING));
    }

    @Test
    public void createFrom_ForNullValue_ReturnsPending() {
        LoanRequestStatus status = LoanRequestStatus.createFrom(null);
        assertThat(status, is(LoanRequestStatus.PENDING));
    }

    @Test
    public void createFrom_ForNonExistingValue_ReturnsPending() {
        LoanRequestStatus status = LoanRequestStatus.createFrom("wrong");
        assertThat(status, is(LoanRequestStatus.PENDING));
    }
}