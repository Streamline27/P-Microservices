package lv.citadele.zuul;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponseOptions;
import io.restassured.specification.RequestSpecification;
import lv.citadele.zuul.dto.LoanRequestModel;
import org.springframework.stereotype.Component;

public class ApiClient {

    private final String CREATE_REQUEST = "http://localhost:8080/process-node/requests";
    private final String VALIDATE_REQUEST = "http://localhost:8080/process-node/requests/{id}/validate";
    private final String CONFIRM_REQUEST = "http://localhost:8080/process-node/requests/{id}/confirm";
    private final String LOAN_REQUEST = "http://localhost:8080/loans-node/loans/{id}";

    public ValidatableResponseOptions createRequest(LoanRequestModel model) {
        return httpRequest()
                .body(model)
                .post(CREATE_REQUEST)
                .then()
                .log().body()
                .and();
    }

    public ValidatableResponseOptions validateRequest(Long requestId) {
        return httpRequest()
                .pathParam("id", requestId)
                .patch(VALIDATE_REQUEST)
                .then()
                .log().body()
                .and();
    }

    public ValidatableResponseOptions confirmRequest(Long requestId) {
        return httpRequest()
            .pathParam("id", requestId)
            .patch(CONFIRM_REQUEST)
            .then()
            .log().body()
            .and();
    }

    public ValidatableResponseOptions getLoan(Long requestId) {
        return httpRequest()
                .pathParam("id", requestId)
                .get(LOAN_REQUEST)
                .then()
                .log().body()
                .and();
    }

    private RequestSpecification httpRequest() {
        return RestAssured.given().with().contentType(ContentType.JSON);
    }
}
