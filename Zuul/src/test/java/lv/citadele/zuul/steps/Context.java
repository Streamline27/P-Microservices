package lv.citadele.zuul.steps;

import lombok.Data;

@Data
public class Context {
    private Long savedRequestId;
    private Long requestId;
    private Long loanId;
}
