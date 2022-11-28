package reward.demo.program.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import reward.demo.program.model.CustomerDTO;
import reward.demo.program.model.MonthlyRewards;
import reward.demo.program.model.TransactionRecordDTO;

@Component
public class DataTransformer {

	public CustomerDTO toCustomerModel(reward.demo.program.entity.Customer custEnt) {
		CustomerDTO cust = new CustomerDTO();
		BeanUtils.copyProperties(custEnt, cust);

		return cust;
	}

	public Map<CustomerDTO, List<TransactionRecordDTO>> splitByCustomerTransactions(List<TransactionRecordDTO> transRecords) {
		Map<CustomerDTO, List<TransactionRecordDTO>> customerTrans = new HashMap<>();

		for (TransactionRecordDTO tranRecord : transRecords) {
			if (!customerTrans.containsKey(tranRecord.getCustomer())) {
				customerTrans.put(tranRecord.getCustomer(), new ArrayList<TransactionRecordDTO>());
			}
			customerTrans.get(tranRecord.getCustomer()).add(tranRecord);
		}

		return customerTrans;

	}
	
	public MonthlyRewards calculateMonthlyReport(CustomerDTO cust, List<TransactionRecordDTO> transRecords) {
		MonthlyRewards monthlyRewards = new MonthlyRewards(cust);
		for (TransactionRecordDTO tranRecord : transRecords) {
			if (!monthlyRewards.getMonthlyTrans().containsKey(tranRecord.getPurchaseDate().getMonth())) {
				monthlyRewards.getMonthlyTrans().put(tranRecord.getPurchaseDate().getMonth(),
						new ArrayList<TransactionRecordDTO>());
			}

			monthlyRewards.getMonthlyTrans().get(tranRecord.getPurchaseDate().getMonth()).add(tranRecord);
		}

		return monthlyRewards;

	}
}
