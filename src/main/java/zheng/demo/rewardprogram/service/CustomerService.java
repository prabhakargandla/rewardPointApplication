package zheng.demo.rewardprogram.service;

import java.util.List;

import zheng.demo.rewardprogram.model.CustomerDTO;
import zheng.demo.rewardprogram.model.TransactionRecordDTO;

public interface CustomerService {

	CustomerDTO findById(Long custId);

	List<TransactionRecordDTO> findThreeMonthTrans();

}