package zheng.demo.rewardprogram.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import zheng.demo.rewardprogram.entity.TransactionRecord;

@Repository
@Transactional
public interface TransactionRecordRepository extends JpaRepository<TransactionRecord, Long> {

	@Query("SELECT trans FROM TransactionRecord trans  WHERE  trans.purchaseDate >= DATEADD(MONTH, -3, CURRENT_DATE) ")
	List<TransactionRecord> findAllInLastThreeMonth();

}
