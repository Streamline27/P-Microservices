package lv.citadele.process.model.dao;

import lv.citadele.process.model.Company;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlackListRepository extends CrudRepository<Company, String> {
}
