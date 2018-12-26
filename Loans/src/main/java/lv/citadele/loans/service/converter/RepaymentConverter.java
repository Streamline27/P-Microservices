package lv.citadele.loans.service.converter;

import lv.citadele.loans.api.RepaymentDTO;
import lv.citadele.loans.model.Repayment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RepaymentConverter {

    public List<RepaymentDTO> toDto(List<Repayment> repayments) {
        return repayments.stream().map(this::toDto).collect(Collectors.toList());
    }

    private RepaymentDTO toDto(Repayment repayment) {
        return new RepaymentDTO(
                repayment.getDate(),
                repayment.getPrincipal(),
                repayment.getCommission()
        );
    }
}
