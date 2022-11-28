package reward.demo.program.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import reward.demo.program.entity.Customer;

@Repository
@Transactional
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	@Query("SELECT cust FROM Customer cust  WHERE cust.name= (:name)")
	Customer findByName(@Param("name") String name);

}
