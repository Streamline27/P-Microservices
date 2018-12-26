package lv.citadele.process.model.dao;

import lv.citadele.process.model.LoanRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface LoanRequestRepository extends CrudRepository<LoanRequest, Long> {
    @Override
    List<LoanRequest> findAll();
    Long countAllByCompanyRegistrationNumberAndCreatedDateBetween(String registrationNumber, Date startDate, Date endDate);
}
