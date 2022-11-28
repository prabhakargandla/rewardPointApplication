package reward.demo.program.service;

import java.util.List;

import reward.demo.program.model.CustomerDTO;
import reward.demo.program.model.TransactionRecordDTO;

public interface CustomerService {

	CustomerDTO findById(Long custId);

	List<TransactionRecordDTO> findThreeMonthTrans();

}