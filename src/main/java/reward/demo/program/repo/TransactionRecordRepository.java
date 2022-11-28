package reward.demo.program.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import reward.demo.program.entity.TransactionRecord;

@Repository
@Transactional
public interface TransactionRecordRepository extends JpaRepository<TransactionRecord, Long> {

	@Query("SELECT trans FROM TransactionRecord trans  WHERE  trans.purchaseDate >= DATEADD(MONTH, -3, CURRENT_DATE) ")
	List<TransactionRecord> findAllInLastThreeMonth();

}
